package br.com.dio.soccernews.domain.repository

import br.com.dio.soccernews.domain.model.News

interface NewsRepository {

    suspend fun getAllNews(): List<News>

    suspend fun findAllFavorites(): List<News>

    suspend fun insertOrReplaceFavorite(news: News)

    suspend fun deleteFavorite(news: News)

}
