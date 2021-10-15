package com.example.screen.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screen.data.database.entity.CurrentEntity

@Dao
interface CurrentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(current: CurrentEntity)

    @Query("SELECT * from current")
    fun getCurrent(): List<CurrentEntity>

    @Query("DELETE FROM current")
    fun deleteAll()

}