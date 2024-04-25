package br.com.dio.soccernews.ui.news

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dio.soccernews.data.local.AppDatabaseFactoryOld
import br.com.dio.soccernews.data.remote.SoccerNewsApiFactoryOld
import br.com.dio.soccernews.data.repository.NewsRepositoryImpl

/**
 * @Deprecated
 * Now uses Retrofit for singleton and dependency injection
 */
class NewsViewModelFactoryOld(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            application = application,
            newsRepository = NewsRepositoryImpl(
                soccerNewsApi = SoccerNewsApiFactoryOld.getInstance(),
                appDatabase = AppDatabaseFactoryOld.getInstance(application.applicationContext)
            )
        ) as T
    }

}
