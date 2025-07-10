package com.example.quotesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.R
import com.example.quotesapp.database.DatabaseViewmodel
import com.example.quotesapp.models.Quotes
import com.example.quotesapp.share.shareText
import com.example.quotesapp.ui.theme.AppThemeOrange
import com.example.quotesapp.ui.theme.AquaMint
import com.example.quotesapp.ui.theme.BlushPink
import com.example.quotesapp.ui.theme.ForestGrayBlue
import com.example.quotesapp.ui.theme.LightCloud
import com.example.quotesapp.ui.theme.RoseFog
import com.example.quotesapp.ui.theme.Typography
import com.example.quotesapp.ui.theme.sand

@Composable
fun QuoteBox(quote: Quotes, modifier: Modifier = Modifier){
    val quoteBackgroundColor = listOf(
        BlushPink,
        AquaMint,
        ForestGrayBlue,
        LightCloud,
        RoseFog,
        sand
    )
    val context = LocalContext.current
    val viewModel: DatabaseViewmodel = hiltViewModel()
    var isInFav by remember { mutableStateOf(false) }
    LaunchedEffect(quote.q) {
        viewModel.getAllSavedQuotes()
        isInFav = viewModel.isInFav(quote.q)
    }

    Box(
        modifier = modifier.fillMaxWidth()
            .border(2.dp, AppThemeOrange, RoundedCornerShape(8.dp))
            .background(quoteBackgroundColor.random(), RoundedCornerShape(8.dp))
    ){
        Column {
            Text(
                quote.q,
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                fontFamily = FontFamily(Font(R.font.playfairdisplay_black)),
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black
            )

            Row {
                Icon(
                    painter = if (isInFav) painterResource(R.drawable.heart_filled)
                              else painterResource(R.drawable.heart),
                    contentDescription = "Add to favourites",
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                        .size(30.dp)
                        .clickable(
                            onClick = {
                                if (isInFav){
                                    viewModel.removeFromFav(quote)
                                }else{
                                    viewModel.addToFav(quote)
                                }
                                isInFav = !isInFav
                            }
                        ),
                    tint = Color.Black
                )

                Icon(
                    painter = painterResource(R.drawable.share),
                    contentDescription = "Share",
                    modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
                        .size(30.dp)
                        .clickable(
                            onClick = {
                                context.startActivity(shareText(quote.q, quote.a))
                            }
                        ),
                    tint = Color.Black
                )

                Text(
                    quote.a,
                    modifier = Modifier.padding(end = 8.dp, bottom = 8.dp).fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.playfairdisplay_black)),
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.End,
                    color = Color.Black
                )
            }
        }
    }
}