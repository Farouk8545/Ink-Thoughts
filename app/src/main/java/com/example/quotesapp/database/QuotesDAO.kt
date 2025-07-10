package com.example.quotesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.quotesapp.models.Quotes

@Dao
interface QuotesDAO {
    @Insert
    suspend fun addToFav(quote: Quotes)

    @Delete
    suspend fun removeFromFav(quote: Quotes)

    @Query("SELECT * FROM SavedQuotes")
    suspend fun getAllSavedQuotes(): List<Quotes>

    @Query("DELETE FROM SavedQuotes")
    suspend fun clearFavourites()

}