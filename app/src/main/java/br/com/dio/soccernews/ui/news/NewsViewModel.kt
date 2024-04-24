package br.com.dio.soccernews.ui.news

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.dio.soccernews.R
import br.com.dio.soccernews.data.repository.NewsRepositoryImpl
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.domain.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpURLConnection

class NewsViewModel(
    private val application: Application,
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getAllNews()
    }

    private fun getAllNews() = viewModelScope.launch {
        runCatching {
            newsRepository.getAllNews()
        }.onSuccess { newsList ->
            _news.value = newsList
        }.onFailure { exception: Throwable ->
            when (exception) {
                is HttpException -> {
                    when (exception.code()) {
                        HttpURLConnection.HTTP_NOT_FOUND -> _error
                            .value = getResourceString(R.string.resource_not_found)

                        else -> _error
                            .value = getResourceString(R.string.resource_fetch_error)
                    }
                }

                else -> throw exception
            }
        }
    }

    private fun getResourceString(resourceId: Int): String {
        return application.resources.getString(resourceId)
    }

}

class NewsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            application = application,
            newsRepository = NewsRepositoryImpl()
        ) as T
    }

}
