package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AsteroidEntity::class, PictureOfDayEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao

    companion object {
        @Volatile private lateinit var INSTANCE: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "asteroid"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

    }

}