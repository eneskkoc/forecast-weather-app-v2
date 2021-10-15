package com.example.screen.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screen.data.database.entity.CoordiEntity

@Dao
interface CoordiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoordi(coordinate: CoordiEntity)

    @Query("SELECT * from coordinate")
    fun getLat(): List<CoordiEntity>

    @Query("SELECT * from coordinate where lower(name) = lower(:name) COllATE LOCALIZED")
    fun getName(name: String): List<CoordiEntity>

    @Query("DELETE  from coordinate where lower(name) = lower(:name) COllATE LOCALIZED")
    fun deleteCoordinate(name: String)


// hepsini silme parametresiz
     @Query("DELETE FROM coordinate")
     fun deleteAll()


}