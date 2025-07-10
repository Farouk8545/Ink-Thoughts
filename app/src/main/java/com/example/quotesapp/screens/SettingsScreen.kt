package com.example.quotesapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.darktheme.ThemeViewModel
import com.example.quotesapp.database.DatabaseViewmodel
import com.example.quotesapp.ui.theme.AppThemeOrange
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun SettingsScreen(){
    val databaseViewModel: DatabaseViewmodel = hiltViewModel()
    val themeViewmodel: ThemeViewModel = hiltViewModel()
    val isDarkTheme by themeViewmodel.isDarkTheme.collectAsState()
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ){
            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(AppThemeOrange, RoundedCornerShape(8.dp)),
                onClick = {
                    databaseViewModel.clearFavourites()
                    Toast.makeText(context, "All quotes have been removed from favourites", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonColors(
                    containerColor = AppThemeOrange,
                    contentColor = Color.White,
                    disabledContainerColor = AppThemeOrange,
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "Clear Favourites",
                    fontSize = 24.sp
                )
            }

            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(AppThemeOrange, RoundedCornerShape(8.dp)),
                onClick = {

                },
                colors = ButtonColors(
                    containerColor = AppThemeOrange,
                    contentColor = Color.White,
                    disabledContainerColor = AppThemeOrange,
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "About",
                    fontSize = 24.sp
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ){
                Text(
                    text = "Dark Theme",
                    fontSize = 24.sp,
                    modifier = Modifier.weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Switch(
                    checked = isDarkTheme,
                    onCheckedChange = {
                        themeViewmodel.toggleTheme(it)
                    }
                )
            }
        }
    }
}