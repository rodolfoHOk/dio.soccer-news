package br.com.dio.soccernews.ui.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.soccernews.R
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.domain.repository.NewsRepository
import br.com.dio.soccernews.ui.commons.state.ActionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val application: Application,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    private val _Action_state = MutableLiveData<ActionState>()
    val actionState: LiveData<ActionState> = _Action_state

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun favorite(newsToFavorite: News) = viewModelScope.launch(Dispatchers.IO) {
        if (newsToFavorite.favorite) {
            newsRepository.deleteFavorite(newsToFavorite)
            getAllNews()
        } else {
            newsToFavorite.favorite = true
            newsRepository.insertOrReplaceFavorite(newsToFavorite)
            getAllNews()
        }
    }

    fun getAllNews() = viewModelScope.launch(Dispatchers.IO) {
        _Action_state.postValue(ActionState.DOING)
        runCatching {
            newsRepository.getAllNews()
        }.onSuccess { news ->
            _newsList.postValue(news)
            _Action_state.postValue(ActionState.DONE)
        }.onFailure { exception: Throwable ->
            when (exception) {
                is HttpException -> {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_NOT_FOUND -> {
                            _errorMessage.postValue(getResourceString(R.string.resource_not_found))
                            _Action_state.postValue(ActionState.ERROR)
                        }

                        else -> {
                            _errorMessage.postValue(getResourceString(R.string.resource_fetch_error))
                            _Action_state.postValue(ActionState.ERROR)
                        }
                    }
                }

                else -> {
                    Log.e("App error", exception.stackTraceToString())
                    _errorMessage.postValue(getResourceString(R.string.resource_fetch_error))
                    _Action_state.postValue(ActionState.ERROR)
                }
            }
        }
    }

    private fun getResourceString(resourceId: Int): String {
        return application.resources.getString(resourceId)
    }

}
