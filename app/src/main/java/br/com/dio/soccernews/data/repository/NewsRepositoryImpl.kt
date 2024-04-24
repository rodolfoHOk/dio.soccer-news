package br.com.dio.soccernews.data.repository

import br.com.dio.soccernews.data.remote.SoccerNewsApi
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.domain.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepositoryImpl : NewsRepository {

    override suspend fun getAllNews(): List<News> {
        return soccerNewsApi.getAllNews()
    }

    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rodolfohok.github.io/dio.soccer-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val soccerNewsApi: SoccerNewsApi = retrofit.create(SoccerNewsApi::class.java)
    }

}
