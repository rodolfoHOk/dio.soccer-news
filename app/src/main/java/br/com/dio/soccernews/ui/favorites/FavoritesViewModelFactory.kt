package br.com.dio.soccernews.ui.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dio.soccernews.data.local.AppDatabaseFactory
import br.com.dio.soccernews.data.remote.SoccerNewsApiFactory
import br.com.dio.soccernews.data.repository.NewsRepositoryImpl

class FavoritesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel(
            application = application,
            newsRepository = NewsRepositoryImpl(
                soccerNewsApi = SoccerNewsApiFactory.getInstance(),
                appDatabase = AppDatabaseFactory.getInstance(application.applicationContext)
            )
        ) as T
    }

}
