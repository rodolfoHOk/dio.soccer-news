package br.com.dio.soccernews.domain.model

data class News(
    val id: Long,
    val title: String,
    val headline: String,
    val image: String,
    val link: String,
    val favorite: Boolean
)
