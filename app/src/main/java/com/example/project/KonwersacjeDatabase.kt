package com.example.project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Konwersacje::class], version = 1)
abstract class KonwersacjeDatabase : RoomDatabase() {
    abstract fun KonwersacjeDAO(): KonwersacjeDAO


}

object KonwersacjeDb{
    private var db: KonwersacjeDatabase? = null

    fun getInstance(context: Context): KonwersacjeDatabase {
        if (db == null) {
            db = Room.databaseBuilder(
                context,
                KonwersacjeDatabase::class.java,
                "konwersacje-database"
            ).build()
        }
        return db!!
    }
}