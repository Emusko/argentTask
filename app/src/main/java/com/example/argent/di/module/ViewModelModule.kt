package com.example.argent.di.module

import androidx.lifecycle.ViewModel
import com.example.argent.di.ViewModelKey
import com.example.argent.presentation.main.MainViewModel
import com.example.argent.presentation.home.HomeViewModel
import com.example.argent.presentation.list.TokenListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TokenListViewModel::class)
    abstract fun bindCoinListViewModel(viewModel: TokenListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}