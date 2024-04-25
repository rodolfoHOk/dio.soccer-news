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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val application: Application,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _favoritesNewsList = MutableLiveData<List<News>>(listOf())
    val favoritesNewsList: LiveData<List<News>> = _favoritesNewsList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun removeFavorite(news: News) = viewModelScope.launch(Dispatchers.IO) {
        newsRepository.deleteFavorite(news)
        withContext(Dispatchers.Main) {
            _favoritesNewsList.value = favoritesNewsList.value?.minus(news)
        }
    }

    fun findAllFavorites() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            newsRepository.findAllFavorites()
        }.onSuccess { newsList ->
            withContext(Dispatchers.Main) {
                _favoritesNewsList.value = newsList
            }
        }.onFailure { exception: Throwable ->
            withContext(Dispatchers.Main) {
                Log.e("App error", exception.stackTraceToString())
                _error.value = getResourceString(R.string.data_recover_error)
            }
        }
    }

    private fun getResourceString(resourceId: Int): String {
        return application.resources.getString(resourceId)
    }

}
