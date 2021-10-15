package com.example.screen.data.database

import android.app.Application
import androidx.room.Room
import com.example.screen.di.PerApplication
import dagger.Module
import dagger.Provides

@Module
class DBModule {
    @PerApplication
    @Provides
    fun database(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        WeatherDatabase::class.java, "weather"
    )
        .build()
}