package com.example.quotesapp.screens

import androidx.navigation3.runtime.NavKey
import com.example.quotesapp.models.Quotes
import kotlinx.serialization.Serializable

sealed class Screens: NavKey{
    @Serializable
    data object HomeScreen: Screens()

    @Serializable
    data object SearchScreen: Screens()

    @Serializable
    data object SettingsScreen: Screens()

    @Serializable
    data object FavouritesScreen: Screens()

    @Serializable
    data class QuotesScreen(val keyword: String?): Screens()

    @Serializable
    data object RandomImageScreen: Screens()
}