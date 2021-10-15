package com.example.screen.di

import com.example.screen.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Bindings {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}