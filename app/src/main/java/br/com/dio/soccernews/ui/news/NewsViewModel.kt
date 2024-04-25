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
import br.com.dio.soccernews.ui.commons.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val application: Application,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableLiveData<List<News>>(listOf())
    val newsList: LiveData<List<News>> = _newsList

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun favorite(newsToFavorite: News) = viewModelScope.launch(Dispatchers.IO) {
        if (newsToFavorite.favorite) {
            newsRepository.deleteFavorite(newsToFavorite)
            withContext(Dispatchers.Main) {
                _newsList.value = newsList.value?.map { news ->
                    if (news.id == newsToFavorite.id) {
                        news.favorite = false
                    }
                    news
                }?.toList()
            }
        } else {
            newsToFavorite.favorite = true
            newsRepository.insertOrReplaceFavorite(newsToFavorite)
            withContext(Dispatchers.Main) {
                _newsList.value = newsList.value?.map { news ->
                    if (news.id == newsToFavorite.id) {
                        news.favorite = true
                    }
                    news
                }?.toList()
            }
        }
    }

    fun getAllNews() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            _state.value = State.DOING
        }
        runCatching {
            newsRepository.getAllNews()
        }.onSuccess { news ->
            withContext(Dispatchers.Main) {
                _newsList.value = news
                _state.value = State.DONE
            }
        }.onFailure { exception: Throwable ->
            when (exception) {
                is HttpException -> {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_NOT_FOUND -> withContext(Dispatchers.Main) {
                            _errorMessage.value = getResourceString(R.string.resource_not_found)
                            _state.value = State.ERROR
                        }

                        else -> withContext(Dispatchers.Main) {
                            _errorMessage.value = getResourceString(R.string.resource_fetch_error)
                            _state.value = State.ERROR
                        }
                    }
                }

                else -> withContext(Dispatchers.Main) {
                    Log.e("App error", exception.stackTraceToString())
                    _errorMessage.value = getResourceString(R.string.resource_fetch_error)
                    _state.value = State.ERROR
                }
            }
        }
    }

    private fun getResourceString(resourceId: Int): String {
        return application.resources.getString(resourceId)
    }

}
