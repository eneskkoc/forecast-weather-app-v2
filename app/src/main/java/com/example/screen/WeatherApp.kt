package com.example.screen

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.screen.di.DaggerAppComponent
class WeatherApp: DaggerApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun applicationInjector(): AndroidInjector<out WeatherApp> {
    return DaggerAppComponent.builder().app(this).build()
    }
}
