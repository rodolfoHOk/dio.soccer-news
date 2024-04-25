package br.com.dio.soccernews.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SoccerNewsApiFactory {

    companion object {

        @Volatile
        private var instance: SoccerNewsApi? = null

        fun getInstance(): SoccerNewsApi {
            return instance ?: synchronized(this) {
                instance ?: createInstance(getRetrofit()).also { soccerNewsApi ->
                    instance = soccerNewsApi
                }
            }
        }

        private fun createInstance(retrofit: Retrofit): SoccerNewsApi {
            return retrofit.create(SoccerNewsApi::class.java)
        }

        private fun getRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl("https://rodolfohok.github.io/dio.soccer-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}
