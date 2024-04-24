package br.com.dio.soccernews.domain.repository

import br.com.dio.soccernews.domain.model.News

interface NewsRepository {

    suspend fun getAllNews() : List<News>

}
