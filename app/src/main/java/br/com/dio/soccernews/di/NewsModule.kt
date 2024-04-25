package br.com.dio.soccernews.di

import br.com.dio.soccernews.data.repository.NewsRepositoryImpl
import br.com.dio.soccernews.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NewsModule {

    @Binds
    abstract fun providesNewsRepository(newsRepositoryImpl: NewsRepositoryImpl) : NewsRepository

}
