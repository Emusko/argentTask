package com.example.argent.di.module

import com.example.argent.di.base.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class BaseModule {
    @Provides
    fun provideActivity() = BaseApplication()
}