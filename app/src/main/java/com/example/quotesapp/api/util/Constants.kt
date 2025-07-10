package com.example.quotesapp.api.util

import androidx.compose.ui.res.painterResource
import com.example.quotesapp.R
import com.example.quotesapp.models.CategoryItem

class Constants {
    companion object{
        const val BASE_URL = "https://zenquotes.io/api/"
        val keywordList = listOf(
            "Anxiety", "Change", "Choice", "Confidence",
            "Courage", "Death", "Dreams", "Excellence",
            "Failure", "Fairness", "Fear", "Forgiveness",
            "Freedom", "Future", "Happiness", "Inspiration",
            "Kindness", "Leadership", "Life", "Living",
            "Love", "Pain", "Past", "Success",
            "Time", "Today", "Truth", "Work"
        )
        val popularCategory = listOf(
            CategoryItem(keywordList[3], R.drawable.confidence),
            CategoryItem(keywordList[14], R.drawable.happiness),
            CategoryItem(keywordList[17], R.drawable.leadership),
            CategoryItem(keywordList[15], R.drawable.inspiratoin),
            CategoryItem(keywordList[26], R.drawable.truth),
        )
        val trendingCategory = listOf(
            CategoryItem(keywordList[23], R.drawable.success),
            CategoryItem(keywordList[27], R.drawable.work),
            CategoryItem(keywordList[20], R.drawable.love),
            CategoryItem(keywordList[16], R.drawable.kindness),
            CategoryItem(keywordList[13], R.drawable.future))
    }
}