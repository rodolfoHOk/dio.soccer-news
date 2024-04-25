package br.com.dio.soccernews.data.repository

import br.com.dio.soccernews.data.local.AppDatabase
import br.com.dio.soccernews.data.local.mapper.toDomain
import br.com.dio.soccernews.data.local.mapper.toEntity
import br.com.dio.soccernews.data.remote.SoccerNewsApi
import br.com.dio.soccernews.domain.model.News
import br.com.dio.soccernews.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val soccerNewsApi: SoccerNewsApi,
    private val appDatabase: AppDatabase
) : NewsRepository {

    override suspend fun getAllNews(): List<News> {
        return soccerNewsApi.getAllNews()
    }

    override suspend fun findAllFavorites(): List<News> {
        return appDatabase.newsDao().findAllFavorites().toDomain()
    }

    override suspend fun insertOrReplace(news: News) {
        appDatabase.newsDao().insertOrReplace(news = news.toEntity())
    }

    override suspend fun delete(news: News) {
        appDatabase.newsDao().delete(news = news.toEntity())
    }

}
