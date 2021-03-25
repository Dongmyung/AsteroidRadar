package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.http.DELETE

@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(asteroids: List<AsteroidEntity>)

    @Query("SELECT * FROM asteroids ORDER BY close_approach_date ASC")
    fun getAllAsteroids(): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids WHERE close_approach_date = :date")
    fun getAsteroidsByDate(date: Long): LiveData<List<AsteroidEntity>>

    @Query("SELECT * FROM asteroids WHERE close_approach_date BETWEEN :startDate AND :endDate ORDER BY close_approach_date ASC")
    fun getAsteroidsByDuration(startDate:Long, endDate:Long): LiveData<List<AsteroidEntity>>

    @Query("DELETE FROM asteroids")
    suspend fun clearAllAsteroids()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictureOfDay(pictureOfDay: PictureOfDayEntity)

    @Query("SELECT * FROM picture_of_day LIMIT 0, 1")
    fun getPicturOfDay(): LiveData<PictureOfDayEntity>

}