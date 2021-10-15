package com.example.screen.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screen.data.database.entity.ForecastEntity

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: ForecastEntity)

    @Query("SELECT * from forecast")
    fun getDaily(): List<ForecastEntity>

    @Query("DELETE FROM forecast")
    fun deleteAll()

}
