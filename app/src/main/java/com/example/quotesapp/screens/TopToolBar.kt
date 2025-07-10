package com.example.quotesapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.quotesapp.R
import com.example.quotesapp.navigation.MainNavigationViewModel
import com.example.quotesapp.ui.theme.AppThemeOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopToolBar(viewModel: MainNavigationViewModel){
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "Ink Thoughts",
                    color = AppThemeOrange,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(
                    Font(R.font.playfairdisplay_black, weight = FontWeight.Bold)
                ))
            }
        },
        navigationIcon = {
            if (viewModel.backStack.lastOrNull() is Screens.QuotesScreen || viewModel.backStack.lastOrNull() is Screens.RandomImageScreen){
                IconButton(
                    onClick = {
                        viewModel.backStack.removeLastOrNull()
                    }
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        }
    )
}