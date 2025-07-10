package com.example.quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.darktheme.ThemeViewModel
import com.example.quotesapp.navigation.MainNavigation
import com.example.quotesapp.navigation.MainNavigationViewModel
import com.example.quotesapp.screens.BottomNavigationBar
import com.example.quotesapp.screens.Screens
import com.example.quotesapp.screens.TopToolBar
import com.example.quotesapp.ui.theme.QuotesAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()
            QuotesAppTheme(darkTheme = isDarkTheme) {
                val viewModel = viewModel<MainNavigationViewModel>()
                val backStack = viewModel.backStack
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            backStack.lastOrNull(),
                            onScreenSelected = {
                                if (it is Screens.HomeScreen){
                                    viewModel.backStack.clear()
                                }else{
                                    viewModel.backStack.clear()
                                    viewModel.backStack.add(Screens.HomeScreen)
                                }
                                viewModel.backStack.add(it)
                            }
                        )
                    },
                    topBar = {
                        TopToolBar(viewModel)
                    }
                ){ padding->
                    Box(
                        modifier = Modifier.padding(padding).fillMaxSize()
                    ){
                        MainNavigation()
                    }
                }
            }
        }
    }
}