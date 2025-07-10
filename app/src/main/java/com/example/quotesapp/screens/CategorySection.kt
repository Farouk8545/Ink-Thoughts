package com.example.quotesapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quotesapp.models.CategoryItem
import com.example.quotesapp.ui.theme.AppThemeOrange
import com.example.quotesapp.ui.theme.Typography

@Composable
fun Category(category: String, categoryItems: List<CategoryItem>, modifier: Modifier = Modifier, onClick: (categoryItem: CategoryItem) -> Unit){
    Box(
        modifier = modifier.fillMaxWidth()
            .border(2.dp, AppThemeOrange, RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ){
        Text(category, style = Typography.bodyMedium, modifier = Modifier.padding(vertical = 4.dp))
    }

    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(categoryItems){ categoryItem->
            Column(
                modifier = modifier.padding(top = 16.dp, start = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(
                    modifier = modifier.clip(CircleShape)
                        .size(100.dp),
                    onClick = {
                        onClick(categoryItem)
                    }
                ) {
                    Image(
                        painter = painterResource(categoryItem.image),
                        contentDescription = categoryItem.keyword,
                        contentScale = ContentScale.Crop
                    )
                }
                Text(categoryItem.keyword)
            }
        }
    }
}