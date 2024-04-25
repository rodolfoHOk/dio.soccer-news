package br.com.dio.soccernews.di

import br.com.dio.soccernews.data.remote.SoccerNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SoccerNewsApiModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rodolfohok.github.io/dio.soccer-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesSoccerNewsApi(retrofit: Retrofit) : SoccerNewsApi {
        return retrofit.create(SoccerNewsApi::class.java)
    }

}
