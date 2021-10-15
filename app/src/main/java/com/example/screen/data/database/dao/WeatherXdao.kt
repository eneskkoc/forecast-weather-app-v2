package com.example.screen.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screen.data.database.entity.WeatherXentity

@Dao
interface WeatherXdao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherX(weatherX: WeatherXentity)

    @Query("SELECT * from weather")
    fun getWeatherX(): List<WeatherXentity>

    @Query("DELETE FROM weatherX")
    fun deleteAll()


}