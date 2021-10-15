package com.example.screen.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherX")
data class WeatherXentity(
    @ColumnInfo(name = "description")  var description: String?,
    @ColumnInfo(name="icon") var icon: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0
)