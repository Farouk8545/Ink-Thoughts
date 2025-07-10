package com.example.quotesapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.database.DatabaseViewmodel
import com.example.quotesapp.ui.theme.Typography

@Composable
fun FavouritesScreen(){

    val viewModel: DatabaseViewmodel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.getAllSavedQuotes()
    }

    if(viewModel.allSavedQuotes.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "You haven't saved any Quotes yet.",
                style = Typography.bodyLarge
            )
        }
    }else{
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(viewModel.allSavedQuotes) { quote ->
                QuoteBox(quote, Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
        }
    }
}