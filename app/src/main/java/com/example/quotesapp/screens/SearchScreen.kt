package com.example.quotesapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.api.util.Constants.Companion.keywordList
import com.example.quotesapp.navigation.MainNavigationViewModel

@Composable
fun SearchScreen(){
    val navigationViewModel = viewModel<MainNavigationViewModel>()
    val backStack = navigationViewModel.backStack
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val textFieldState = remember { TextFieldState() }
        var searchResults by remember { mutableStateOf(keywordList) }

        fun handleInput(query: String){
            searchResults = if(query.isBlank()){
                keywordList
            }else{
                keywordList.filter { it.contains(query, ignoreCase = true) }
            }
        }

        SearchBar(
            modifier = Modifier,
            searchResults = searchResults,
            textFieldState = textFieldState,
            handleInput = ::handleInput
        ) { query->
            backStack.add(Screens.QuotesScreen(query))
        }
    }
}
