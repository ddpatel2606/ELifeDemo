package com.dixitpatel.elifedemo.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.dixitpatel.elifedemo.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashScreenActivityModule {

    @Provides
    fun providesViewModel(): SplashScreenViewModel {
        return SplashScreenViewModel()
    }

    @Provides
    fun provideViewModelProvider(viewModel: SplashScreenViewModel?): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}