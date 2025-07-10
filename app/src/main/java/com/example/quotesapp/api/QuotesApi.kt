package com.example.quotesapp.api

import com.example.quotesapp.models.Quotes
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    @GET("quotes")
    suspend fun getRandomQuotes(@Query("keyword") keyword: String?): Response<List<Quotes>>

    @GET("today")
    suspend fun getTodayQuote(): Response<List<Quotes>>

    @GET("random")
    suspend fun getSingleRandomQuote(): Response<Quotes>

    @GET("image")
    suspend fun getRandomImage(): Response<ResponseBody>

}