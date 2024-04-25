package br.com.dio.soccernews.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("news")
data class NewsEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val headline: String,
    val image: String,
    val link: String,
    val favorite: Boolean

)
