package br.com.dio.soccernews.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.dio.soccernews.data.local.entity.NewsEntity

@Dao
interface NewsDao {

    @Query("SELECT * FROM news WHERE favorite = :favorite")
    fun findAllFavorites(favorite: Boolean = true) : List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(news: NewsEntity)

    @Delete
    fun delete(news: NewsEntity)

}
