package com.example.quotesapp.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.quotesapp.ui.theme.AppThemeOrange

data class BottomNavItem(
    val screen: Screens,
    val icon: ImageVector,
    val label: String
)

private val bottomNavItems = listOf(
    BottomNavItem(
        screen = Screens.HomeScreen,
        icon = Icons.Default.Home,
        label = "Home"
    ),
    BottomNavItem(
        screen = Screens.SearchScreen,
        icon = Icons.Default.Search,
        label = "Search"
    ),
    BottomNavItem(
        screen = Screens.FavouritesScreen,
        icon = Icons.Default.Favorite,
        label = "Favourites"
    ),
    BottomNavItem(
        screen = Screens.SettingsScreen,
        icon = Icons.Default.Settings,
        label = "Settings"
    )
)

@Composable
fun BottomNavigationBar(
    currentScreen: Screens?,
    onScreenSelected: (Screens) -> Unit
){
    NavigationBar {
        bottomNavItems.forEach {
            NavigationBarItem(
                selected = currentScreen == it.screen,
                onClick = { onScreenSelected(it.screen) },
                icon = { Icon(imageVector = it.icon, contentDescription = it.label) },
                label = { Text(text = it.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    indicatorColor = AppThemeOrange,
                    selectedTextColor = AppThemeOrange
                )
            )
        }
    }
}