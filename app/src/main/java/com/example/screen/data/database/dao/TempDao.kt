package com.example.screen.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screen.data.database.entity.TempEntity

@Dao
interface TempDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTemp(temp: TempEntity)

    @Query("SELECT * from `temp`")
    fun getTemp(): List<TempEntity>

    @Query("DELETE FROM 'temp'")
    fun deleteAll()

}