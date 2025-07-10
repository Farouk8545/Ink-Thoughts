package com.example.quotesapp.models

import androidx.annotation.DrawableRes

data class CategoryItem(
    val keyword: String,
    @DrawableRes val image: Int
)