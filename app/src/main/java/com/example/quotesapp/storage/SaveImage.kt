package com.example.quotesapp.storage

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
fun saveImage(context: Context, bitmap: Bitmap?){
    val contentValues = ContentValues().apply {
        put(MediaStore.Downloads.DISPLAY_NAME, "image_${System.currentTimeMillis()}.png")
        put(MediaStore.Downloads.MIME_TYPE, "image/png")
        put(MediaStore.Downloads.RELATIVE_PATH, "Download/Ink Thoughts")
        put(MediaStore.Downloads.IS_PENDING, 1)
    }

    val resolver = context.contentResolver
    val collection = MediaStore.Downloads.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    val uri = resolver.insert(collection, contentValues)

    uri?.let {
        resolver.openOutputStream(it)?.use { outputStream ->
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }
        contentValues.clear()
        contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
        resolver.update(it, contentValues, null, null)
    }
}