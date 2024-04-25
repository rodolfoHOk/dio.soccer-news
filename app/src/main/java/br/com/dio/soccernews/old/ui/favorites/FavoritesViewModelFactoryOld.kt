package br.com.dio.soccernews.old.ui.favorites

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dio.soccernews.old.data.local.AppDatabaseFactoryOld
import br.com.dio.soccernews.old.data.remote.SoccerNewsApiFactoryOld
import br.com.dio.soccernews.data.repository.NewsRepositoryImpl
import br.com.dio.soccernews.ui.favorites.FavoritesViewModel

/**
 * @Deprecated
 * Now uses Retrofit for singleton and dependency injection
 */
class FavoritesViewModelFactoryOld(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel(
            application = application,
            newsRepository = NewsRepositoryImpl(
                soccerNewsApi = SoccerNewsApiFactoryOld.getInstance(),
                appDatabase = AppDatabaseFactoryOld.getInstance(application.applicationContext)
            )
        ) as T
    }

}
