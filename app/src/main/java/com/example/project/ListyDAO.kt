package com.example.project

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ListyDAO {

    @Query("SELECT * FROM konwersacje_table")
    fun getAll(): Flow<List<KonwersacjeRelation>>

    @Insert(onConflict = REPLACE)
    suspend fun insertall1(value: List<lista1>)

    @Insert(onConflict = REPLACE)
    suspend fun insertall2(value: List<lista2>)
}