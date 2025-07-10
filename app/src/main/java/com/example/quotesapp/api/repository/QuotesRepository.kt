package com.example.quotesapp.api.repository

import com.example.quotesapp.api.RetrofitInstance
import com.example.quotesapp.models.Quotes
import okhttp3.ResponseBody
import retrofit2.Response

class QuotesRepository {
    suspend fun getRandomQuotes(keyword: String?): Response<List<Quotes>> {
        return RetrofitInstance.api.getRandomQuotes(keyword)
    }

    suspend fun getTodayQuotes(): Response<List<Quotes>> {
        return RetrofitInstance.api.getTodayQuote()
    }

    suspend fun getSingleRandomQuote(): Response<Quotes>{
        return RetrofitInstance.api.getSingleRandomQuote()
    }

    suspend fun getRandomImage(): Response<ResponseBody>{
        return RetrofitInstance.api.getRandomImage()
    }

}