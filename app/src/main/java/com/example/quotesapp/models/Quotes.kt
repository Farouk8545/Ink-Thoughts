package com.example.quotesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "SavedQuotes")
@Serializable
data class Quotes(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val q: String,
    val a: String,
    val i: String?,
    val c: String?,
    val h: String?
)