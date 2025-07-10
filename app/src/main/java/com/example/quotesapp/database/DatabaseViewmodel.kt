package com.example.quotesapp.database

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.models.Quotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewmodel @Inject constructor(application: Application): AndroidViewModel(application) {

    var repository: DatabaseRepository

    init {
        val quotesDAO = QuotesDatabase.getDatabase(application).quotesDao()
        repository = DatabaseRepository(quotesDAO)
    }

    var allSavedQuotes by mutableStateOf<List<Quotes>>(emptyList())
        private set

    fun addToFav(quote: Quotes){
        viewModelScope.launch {
            repository.addToFav(quote)
        }
    }

    fun removeFromFav(quote: Quotes){
        viewModelScope.launch {
            repository.removeFromFav(quote)
        }
    }

    fun getAllSavedQuotes(){
        viewModelScope.launch {
            allSavedQuotes = repository.getAllSavedQuotes()
        }
    }

    fun isInFav(q: String): Boolean{
        var found = false
        for (quote in allSavedQuotes){
            if (quote.q == q){
                found = true
                break
            }
        }
        return found
    }

    fun clearFavourites(){
        viewModelScope.launch {
            repository.clearFavourites()
        }
    }

}