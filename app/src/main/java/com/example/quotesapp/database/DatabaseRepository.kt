package com.example.quotesapp.database

import com.example.quotesapp.models.Quotes

class DatabaseRepository(private val quotesDAO: QuotesDAO) {

    suspend fun addToFav(quote: Quotes){
        quotesDAO.addToFav(quote)
    }

    suspend fun removeFromFav(quote: Quotes){
        quotesDAO.removeFromFav(quote)
    }

    suspend fun getAllSavedQuotes(): List<Quotes>{
        return quotesDAO.getAllSavedQuotes()
    }

    suspend fun clearFavourites(){
        quotesDAO.clearFavourites()
    }

}