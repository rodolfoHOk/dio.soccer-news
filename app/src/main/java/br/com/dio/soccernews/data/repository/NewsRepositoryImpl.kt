package br.com.dio.soccernews.data.repository

import br.com.dio.soccernews.data.local.AppDatabase
import br.com.dio.soccernews.data.local.mapper.toDomain
import br.com.dio.soccernews.data.local.mapper.toEntity
import br.com.dio.soccernews.data.remote.SoccerNewsApi
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val soccerNewsApi: SoccerNewsApi,
    private val appDatabase: AppDatabase
) : NewsRepository {

    override suspend fun getAllNews(): List<News> {
        val favoritesIds = appDatabase.newsDao().findAllFavorites().map { favorite ->
            favorite.id
        }
        val newsList = soccerNewsApi.getAllNews().map { news ->
            news.favorite = favoritesIds.contains(news.id)
            news
        }
        return newsList
    }

    override suspend fun findAllFavorites(): List<News> {
        return appDatabase.newsDao().findAllFavorites().toDomain()
    }

    override suspend fun insertOrReplaceFavorite(news: News) {
        appDatabase.newsDao().insertOrReplace(news = news.toEntity())
    }

    override suspend fun deleteFavorite(news: News) {
        appDatabase.newsDao().delete(news = news.toEntity())
    }

}
