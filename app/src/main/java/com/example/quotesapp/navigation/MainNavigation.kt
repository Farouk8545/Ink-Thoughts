package com.example.quotesapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.quotesapp.screens.FavouritesScreen
import com.example.quotesapp.screens.HomeScreen
import com.example.quotesapp.screens.QuotesScreen
import com.example.quotesapp.screens.RandomImageScreen
import com.example.quotesapp.screens.Screens
import com.example.quotesapp.screens.SearchScreen
import com.example.quotesapp.screens.SettingsScreen

@Composable
fun MainNavigation(){
    val viewModel = viewModel<MainNavigationViewModel>()
    val backStack = viewModel.backStack
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screens.HomeScreen> {
                HomeScreen()
            }

            entry<Screens.SearchScreen> {
                SearchScreen()
            }

            entry<Screens.SettingsScreen> {
                SettingsScreen()
            }

            entry<Screens.FavouritesScreen> {
                FavouritesScreen()
            }

            entry<Screens.QuotesScreen> {
                QuotesScreen(it.keyword)
            }

            entry<Screens.RandomImageScreen> {
                RandomImageScreen()
            }
        }
    )
}