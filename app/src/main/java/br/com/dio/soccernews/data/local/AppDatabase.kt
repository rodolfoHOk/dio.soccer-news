package br.com.dio.soccernews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.dio.soccernews.data.local.dao.NewsDao
import br.com.dio.soccernews.data.local.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

}
