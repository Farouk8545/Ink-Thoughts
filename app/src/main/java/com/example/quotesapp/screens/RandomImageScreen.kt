package com.example.quotesapp.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp.R
import com.example.quotesapp.api.viewmodel.QuotesViewModel
import com.example.quotesapp.share.shareImage
import com.example.quotesapp.storage.saveImage
import com.example.quotesapp.ui.theme.AppThemeOrange

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RandomImageScreen(){
    val viewModel: QuotesViewModel = viewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getRandomImage()
    }

    if(viewModel.isLoading){
        CircularProgressBar()
    }else{
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            if (viewModel.errorMessage == null){
                Box(
                    modifier = Modifier.border(2.dp, AppThemeOrange, RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                ) {
                    viewModel.bitMap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Quote Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp),
                            contentScale = ContentScale.FillWidth // forces it to scale width
                        )
                    }
                }
            }else{
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(1.33f)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.image_error),
                        contentDescription = "No image",
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    colors = ButtonColors(
                        containerColor = AppThemeOrange,
                        contentColor = Color.White,
                        disabledContainerColor = AppThemeOrange,
                        disabledContentColor = Color.White
                    ),
                    onClick = {
                        viewModel.getRandomImage()
                    }
                ) {
                    Text("Get another image")
                }
                Row (
                    modifier = Modifier.padding(top = 8.dp)
                ){
                    Icon(
                        painter = painterResource(R.drawable.download),
                        modifier = Modifier.padding(end = 12.dp)
                            .size(30.dp)
                            .clickable(
                                onClick = {
                                    if(viewModel.bitMap != null){
                                        saveImage(context, viewModel.bitMap)
                                        Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
                                    }else{
                                        Toast.makeText(context, "No image found, check you Internet connection",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }
                            ),
                        contentDescription = "Download"
                    )
                    Icon(
                        painter = painterResource(R.drawable.share),
                        contentDescription = "Share",
                        modifier = Modifier.padding(end = 12.dp)
                            .size(30.dp)
                            .clickable(
                                onClick = {
                                    if(viewModel.bitMap != null){
                                        context.startActivity(shareImage(context, viewModel.bitMap))
                                    }else{
                                        Toast.makeText(context, "No image found, check you Internet connection",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                    )
                }
            }
        }
    }
}