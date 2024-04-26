package br.com.dio.soccernews.ui.favorites

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
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val application: Application,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _favoritesNewsList = MutableLiveData<List<News>>(listOf())
    val favoritesNewsList: LiveData<List<News>> = _favoritesNewsList

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun removeFavorite(news: News) = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.deleteFavorite(news)
        findAllFavorites()
    }

    fun findAllFavorites() = viewModelScope.launch(Dispatchers.IO) {
        _state.postValue(State.DOING)
        runCatching {
            newsRepository.findAllFavorites()
        }.onSuccess { newsList ->
            _favoritesNewsList.postValue(newsList)
            _state.postValue(State.DONE)
        }.onFailure { exception: Throwable ->
            Log.e("App error", exception.stackTraceToString())
            _errorMessage.postValue(getResourceString(R.string.data_recover_error))
            _state.postValue(State.ERROR)
        }
    }

    private fun getResourceString(resourceId: Int): String {
        return application.resources.getString(resourceId)
    }

}
