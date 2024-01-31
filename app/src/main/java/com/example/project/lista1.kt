package com.example.project

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "lista1", foreignKeys = [ForeignKey(
        entity = Konwersacje::class,
        parentColumns = ["uid"],
        childColumns = ["konwersacjaid"],
        onDelete = CASCADE
    )],
    indices =
    [
        Index("konwersacjaid")
    ]
)
data class lista1(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val konwersacjaid: Int,
    val tresc: String,

    )

@Entity(
    "lista2", foreignKeys = [ForeignKey(
        entity = Konwersacje::class,
        parentColumns = ["uid"],
        childColumns = ["konwersacjaid"],
        onDelete = CASCADE
    )],
    indices =
    [
        Index("konwersacjaid")
    ]
)
data class lista2(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val konwersacjaid: Int,
    val tresc: String,

    )