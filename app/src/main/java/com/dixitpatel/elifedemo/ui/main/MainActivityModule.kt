package com.dixitpatel.elifedemo.ui.main

import androidx.lifecycle.ViewModelProvider
import com.dixitpatel.elifedemo.db.TasksDao
import com.dixitpatel.elifedemo.network.ApiInterface
import com.dixitpatel.elifedemo.repository.MainRepository
import com.dixitpatel.elifedemo.utils.ViewModelProviderFactory
import com.dixitpatel.elifedemo.repository.Repository
import dagger.Module
import dagger.Provides

/**
 *  Main Activity Module : MainActivity bind with ViewModel
 */
@Module
class MainActivityModule {

    @Provides
    fun providesViewModel(mainViewRepository: MainRepository): MainActivityViewModel {
        return MainActivityViewModel(mainViewRepository)
    }

    @Provides
    fun provideViewModelProvider(viewModel: MainActivityViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}