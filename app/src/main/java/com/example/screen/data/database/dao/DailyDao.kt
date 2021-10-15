package com.example.screen.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screen.data.database.entity.DailyEntity

@Dao
interface DailyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDaily(daily: DailyEntity)

    @Query("SELECT * from daily")
    fun getDaily(): List<DailyEntity>

    @Query("DELETE FROM daily")
    fun deleteAll()


}