package com.example.quotesapp.api.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.api.repository.QuotesRepository
import com.example.quotesapp.models.Quotes
import kotlinx.coroutines.launch
import java.io.IOException

class QuotesViewModel(private val repository: QuotesRepository) : ViewModel() {
    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(true)
        private set

    var quotes by mutableStateOf<List<Quotes>>(emptyList())
        private set

    var todayQuote by mutableStateOf<List<Quotes>?>(null)
        private set

    var singleRandomQuote by mutableStateOf<Quotes?>(null)
        private set

    var bitMap by mutableStateOf<Bitmap?>(null)
        private set

    init {
        getTodayQuote()
    }

    fun getRandomQuotes(keyword: String? = null){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.getRandomQuotes(keyword)
                if(response.isSuccessful){
                    quotes = response.body()!!
                }else{
                    errorMessage = "Error: ${response.message()}"
                }
            }catch (e: IOException){
                errorMessage = "Network Error: ${e.message}"
            }catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            }finally {
                    isLoading = false
            }
        }
    }

    fun getTodayQuote(){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.getTodayQuotes()
                if (response.isSuccessful){
                    todayQuote = response.body()
                }else{
                    errorMessage = "Error ${response.message()}"
                }
            }catch (e: IOException){
                errorMessage = "Network Error ${e.message}"
            }catch (e: Exception){
                errorMessage = "Error ${e.message}"
            }finally {
                isLoading = false
            }
        }
    }

    fun getSingleRandomQuote(){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.getSingleRandomQuote()
                if (response.isSuccessful){
                    singleRandomQuote = response.body()
                }else{
                    errorMessage = "Error ${response.message()}"
                }
            }catch (e: IOException){
                errorMessage = "Network Error ${e.message}"
            }catch (e: Exception){
                errorMessage = "Error ${e.message}"
            }finally {
                isLoading = false
            }
        }
    }

    fun getRandomImage(){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val response = repository.getRandomImage()
                if (response.isSuccessful){
                    bitMap = response.body()?.byteStream()?.let { BitmapFactory.decodeStream(it) }
                }else{
                    errorMessage = "Error ${response.message()}"
                }
            }catch (e: IOException){
                errorMessage = "Network Error ${e.message}"
            }catch (e: Exception){
                errorMessage = "Error ${e.message}"
            }finally {
                isLoading = false
            }
        }
    }
}