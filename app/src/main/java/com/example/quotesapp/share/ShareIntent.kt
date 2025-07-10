package com.example.quotesapp.share

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import java.io.File

fun shareText(quote: String, author: String): Intent{
    val stringBuilder = StringBuilder()
    stringBuilder.append("\"")
    stringBuilder.append(quote)
    stringBuilder.append("\"")
    stringBuilder.append("\n\n")
    stringBuilder.append("- $author".padStart(80-author.length-2))

    val intent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT ,stringBuilder.toString())
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(intent, null)
    return shareIntent
}

fun shareImage(context: Context, bitmap: Bitmap?): Intent{
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        saveBitmapToCache(context, bitmap)
    )

    val intent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/png"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    val shareIntent = Intent.createChooser(intent, null)
    return shareIntent
}

fun saveBitmapToCache(context: Context, bitmap: Bitmap?): File {
    val file = File(context.cacheDir, "shared_image.png")
    file.outputStream().use { out ->
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file
}
