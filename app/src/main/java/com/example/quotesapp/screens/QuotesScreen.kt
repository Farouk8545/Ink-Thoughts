package com.example.quotesapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.api.repository.QuotesRepository
import com.example.quotesapp.api.viewmodel.QuotesViewModel
import com.example.quotesapp.api.viewmodel.QuotesViewModelFactory
import com.example.quotesapp.ui.theme.AppThemeOrange
import com.example.quotesapp.ui.theme.Typography

@Composable
fun QuotesScreen(keyword: String?){
    val repository = remember { QuotesRepository() }
    val viewModel: QuotesViewModel = viewModel(factory = QuotesViewModelFactory(repository))
    LaunchedEffect(Unit) {
        viewModel.getRandomQuotes()
    }
    if(viewModel.isLoading){
        CircularProgressBar()
    } else if(viewModel.errorMessage != null){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = viewModel.errorMessage!!)
        }
    } else if(viewModel.quotes.isEmpty()){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(text = "No Quotes available now")
        }
    } else{
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            item {
                if(keyword != null){
                    Text(
                        text = "Quotes About: $keyword",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = Typography.headlineSmall
                    )
                }
            }
            items(viewModel.quotes) { quotes->
                QuoteBox(quotes, Modifier.padding(horizontal = 12.dp, vertical = 8.dp))
            }
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            viewModel.getRandomQuotes()
                        },
                        colors = ButtonColors(
                            containerColor = AppThemeOrange,
                            contentColor = Color.White,
                            disabledContainerColor = AppThemeOrange,
                            disabledContentColor = Color.White
                        )
                    ) {
                        Text("Get More Quotes")
                    }
                }
            }
        }
    }
}