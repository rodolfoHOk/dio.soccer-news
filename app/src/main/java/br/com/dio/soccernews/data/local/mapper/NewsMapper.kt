package br.com.dio.soccernews.data.local.mapper

import br.com.dio.soccernews.data.local.entity.NewsEntity
import br.com.dio.soccernews.domain.model.News

fun News.toEntity(): NewsEntity = NewsEntity(
    id = this.id,
    title = this.title,
    headline = this.headline,
    image = this.image,
    link = this.link,
    favorite = this.favorite
)

fun NewsEntity.toDomain(): News = News(
    id = this.id,
    title = this.title,
    headline = this.headline,
    image = this.image,
    link = this.link,
    favorite = this.favorite
)

fun List<News>.toEntity(): List<NewsEntity> = this.map { domainModel -> domainModel.toEntity() }

fun List<NewsEntity>.toDomain(): List<News> = this.map { entity -> entity.toDomain() }
