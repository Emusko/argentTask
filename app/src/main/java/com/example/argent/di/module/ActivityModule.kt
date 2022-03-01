package com.example.argent.di.module

import com.example.argent.presentation.main.MainActivity
import com.example.argent.presentation.home.HomeFragment
import com.example.argent.presentation.list.TokenListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun authenticateActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun coinListFragment(): TokenListFragment

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun HomeFragment(): HomeFragment
}