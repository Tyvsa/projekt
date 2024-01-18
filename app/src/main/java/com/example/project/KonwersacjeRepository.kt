package com.example.project

import kotlinx.coroutines.flow.Flow

class KonwersacjeRepository(private val konwersacjeDao: KonwersacjeDAO) {

    suspend fun insert(konwersacje: Konwersacje) {
        konwersacjeDao.insertAll(listOf(konwersacje))
    }

    fun getAll(): Flow<List<Konwersacje>> {
        return konwersacjeDao.getAll()
    }
}