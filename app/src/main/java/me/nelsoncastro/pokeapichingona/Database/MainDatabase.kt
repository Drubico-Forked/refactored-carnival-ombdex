package me.nelsoncastro.pokeapichingona.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.nelsoncastro.pokeapichingona.Database.Domain.MovieDao
import me.nelsoncastro.pokeapichingona.Models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase(){
    abstract val movieDao: MovieDao

    private lateinit var INSTANCE: MainDatabase

    fun getDatabase(context: Context): MainDatabase {
        synchronized(MainDatabase::class) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room
                        .databaseBuilder(context.applicationContext
                                , MainDatabase::class.java
                                ,"movies_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }
        return INSTANCE
    }
}