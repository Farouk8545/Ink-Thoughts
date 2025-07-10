package com.example.quotesapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.api.repository.QuotesRepository
import com.example.quotesapp.api.util.Constants.Companion.popularCategory
import com.example.quotesapp.api.util.Constants.Companion.trendingCategory
import com.example.quotesapp.api.viewmodel.QuotesViewModel
import com.example.quotesapp.api.viewmodel.QuotesViewModelFactory
import com.example.quotesapp.models.Quotes
import com.example.quotesapp.navigation.MainNavigationViewModel
import com.example.quotesapp.ui.theme.AppTextColor
import com.example.quotesapp.ui.theme.AppThemeOrange
import com.example.quotesapp.ui.theme.Typography

@Composable
fun HomeScreen(){
    val repository = remember{ QuotesRepository() }
    val quotesViewModel: QuotesViewModel = viewModel(factory = QuotesViewModelFactory(repository))
    val navigationViewModel = viewModel<MainNavigationViewModel>()
    val backStack = navigationViewModel.backStack

    if (quotesViewModel.isLoading == true){
        CircularProgressBar()
    }else{
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Quote of the day",
                style = Typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(12.dp))

            quotesViewModel.todayQuote?.let { quote->
                QuoteBox(quote.first(), modifier = Modifier.padding(horizontal = 12.dp))
            } ?: run {
                QuoteBox(
                    Quotes(
                        id = null,
                        q = "Something went wrong",
                        a = "",
                        h = "",
                        c = "",
                        i = ""
                    )
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 38.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonColors(
                    containerColor =  AppThemeOrange,
                    contentColor = Color.White,
                    disabledContainerColor = AppThemeOrange,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    backStack.add(Screens.QuotesScreen(null))
                }
            ) {
                Text(
                    "Browse Quotes",
                    style = Typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 38.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonColors(
                    containerColor =  AppThemeOrange,
                    contentColor = Color.White,
                    disabledContainerColor = AppThemeOrange,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    backStack.add(Screens.RandomImageScreen)
                }
            ) {
                Text(
                    "Random Motivational Image",
                    style = Typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Category(
                "Popular Categories",
                popularCategory,
                modifier = Modifier.padding(bottom = 16.dp)
            ) { categoryItem ->
                navigationViewModel.backStack.add(Screens.QuotesScreen(categoryItem.keyword))
            }

            Category(
                "Trending Categories",
                trendingCategory,
                modifier = Modifier.padding(bottom = 16.dp)
            ) { categoryItem ->
                navigationViewModel.backStack.add(Screens.QuotesScreen(categoryItem.keyword))
            }
        }
    }
}