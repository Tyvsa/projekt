package com.example.project

import kotlinx.coroutines.flow.Flow

class KonwersacjeRepository(
    private val konwersacjeDao: KonwersacjeDAO,
    private val listyDAO: ListyDAO
) {

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

    fun getById(uid: Long) {
        konwersacjeDao.getById(uid)
    }

    fun getAllListy(): Flow<List<KonwersacjeRelation>> {
        return listyDAO.getAll()
    }

    suspend fun insertlista1(value: List<lista1>) {
        listyDAO.insertall1(value)
    }

    suspend fun insertlista2(value: List<lista2>) {
        listyDAO.insertall2(value)
    }
}

