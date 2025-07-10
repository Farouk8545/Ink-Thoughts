package com.example.quotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quotesapp.models.Quotes
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Quotes::class], version = 1, exportSchema = false)
abstract class QuotesDatabase: RoomDatabase() {
    abstract fun quotesDao(): QuotesDAO

    companion object {
        @Volatile
        private var INSTANCE: QuotesDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): QuotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuotesDatabase::class.java,
                    "quotes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}