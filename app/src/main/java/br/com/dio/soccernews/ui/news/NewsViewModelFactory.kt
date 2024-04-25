package br.com.dio.soccernews.ui.news

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dio.soccernews.data.local.AppDatabase
import br.com.dio.soccernews.data.remote.SoccerNewsApi
import br.com.dio.soccernews.data.remote.SoccerNewsApiFactory
import br.com.dio.soccernews.data.repository.NewsRepositoryImpl

class NewsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            application = application,
            newsRepository = NewsRepositoryImpl(
                soccerNewsApi = getApiInstance(),
                appDatabase = getDatabaseInstance(application.applicationContext)
            )
        ) as T
    }

    private fun getApiInstance(): SoccerNewsApi {
        return SoccerNewsApiFactory.getInstance()
    }

    private fun getDatabaseInstance(applicationContext: Context): AppDatabase {
        return AppDatabase.getInstance(applicationContext = applicationContext)
    }

}
