package br.com.dio.soccernews.data.remote

import br.com.dio.soccernews.domain.model.News
import retrofit2.http.GET

interface SoccerNewsApi {

    @GET("news.json")
    suspend fun getAllNews() : List<News>

}
