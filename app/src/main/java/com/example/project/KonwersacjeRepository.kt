package com.example.project

import kotlinx.coroutines.flow.Flow

class KonwersacjeRepository(private val konwersacjeDao: KonwersacjeDAO) {

    suspend fun insert(konwersacje: Konwersacje) {
        konwersacjeDao.insertAll(listOf(konwersacje))
    }

    suspend fun update(konwersacje: Konwersacje) {
        konwersacjeDao.update(konwersacje)
    }

    suspend fun delete(konwersacje: Konwersacje) {
        konwersacjeDao.delete(listOf(konwersacje))
    }

    fun getAll(): Flow<List<Konwersacje>> {
        return konwersacjeDao.getAll()
    }

    suspend fun dropDatabase() {
        konwersacjeDao.dropDatabase()
    }
}

