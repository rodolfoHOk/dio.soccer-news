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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection

class NewsViewModel(
    private val application: Application,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableLiveData<List<News>>(listOf())
    val newsList: LiveData<List<News>> = _newsList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getAllNews()
    }

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
        runCatching {
            newsRepository.getAllNews()
        }.onSuccess { news ->
            withContext(Dispatchers.Main) {
                _newsList.value = news
            }
        }.onFailure { exception: Throwable ->
            when (exception) {
                is HttpException -> {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_NOT_FOUND -> withContext(Dispatchers.Main) {
                            _error.value = getResourceString(R.string.resource_not_found)
                        }

                        else -> withContext(Dispatchers.Main) {
                            _error.value = getResourceString(R.string.resource_fetch_error)
                        }
                    }
                }

                else -> withContext(Dispatchers.Main) {
                    Log.e("App error", exception.stackTraceToString())
                    _error.value = getResourceString(R.string.resource_fetch_error)
                }
            }
        }
    }

    private fun getResourceString(resourceId: Int): String {
        return application.resources.getString(resourceId)
    }

}
