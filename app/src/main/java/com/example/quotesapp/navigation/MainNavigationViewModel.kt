package com.example.quotesapp.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.quotesapp.screens.Screens

class MainNavigationViewModel: ViewModel() {
    var backStack = mutableStateListOf<Screens>(Screens.HomeScreen)
}