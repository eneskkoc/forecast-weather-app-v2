package com.example.screen.di

import android.app.Application
import com.example.screen.WeatherApp
import com.example.screen.data.database.DBModule
import com.example.screen.data.service.ApiService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [ AndroidSupportInjectionModule::class,
        Bindings::class,
        ApiService::class,
        DBModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<WeatherApp> {
    fun inject(application: Application)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder
        fun build(): AppComponent
    }
}