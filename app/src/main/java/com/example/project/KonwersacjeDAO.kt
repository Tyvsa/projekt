package com.example.project

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KonwersacjeDAO {
    @Insert
    suspend fun insertAll(konwersacje: List<Konwersacje>)

    @Delete
    suspend fun delete(konwersacje: List<Konwersacje>)

    @Update
    suspend fun update(konwersacje: Konwersacje)

    @Query("SELECT * FROM konwersacje_table")
    fun getAll(): Flow<List<Konwersacje>>

    @Query("DELETE FROM konwersacje_table")
    suspend fun dropDatabase()

    @Query("SELECT * FROM konwersacje_table WHERE uid = :uid LIMIT 1")
    fun getById(uid: Long): Flow<Konwersacje?>
    
}