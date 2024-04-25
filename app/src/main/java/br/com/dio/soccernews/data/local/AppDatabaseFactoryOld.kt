package br.com.dio.soccernews.data.local

import android.content.Context
import androidx.room.Room

/**
 * @Deprecated
 * Now uses Retrofit for singleton and dependency injection
 */
class AppDatabaseFactoryOld {

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(applicationContext: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(applicationContext).also { appDatabase ->
                    instance = appDatabase
                }
            }
        }

        private fun buildDatabase(applicationContext: Context): AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }

        private const val DATABASE_NAME = "soccer-news.db"

    }

}
