package com.example.project

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "konwersacje_table")
data class Konwersacje(
    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,
    val imie1: String,
    val imie2: String,
    val nazwa: String,
)
