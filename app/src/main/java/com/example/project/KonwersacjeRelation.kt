package com.example.project

import androidx.room.Embedded
import androidx.room.Relation

data class KonwersacjeRelation(
    @Embedded val konwersacje: Konwersacje,
    @Relation(
        parentColumn = "uid",
        entityColumn = "konwersacjaid",
        entity = lista1::class
    )
    val list1: List<lista1>,
    @Relation(
        parentColumn = "uid",
        entityColumn = "konwersacjaid",
        entity = lista2::class
    )
    val list2: List<lista2>
)