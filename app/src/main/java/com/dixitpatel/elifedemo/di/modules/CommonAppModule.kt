package com.dixitpatel.elifedemo.di.modules

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.dixitpatel.elifedemo.application.MyApplication
import com.dixitpatel.elifedemo.utils.ConnectivityReceiver
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonAppModule {

    @Singleton
    @Provides
    fun provideContext(application: MyApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideConnectivityReceiver(context: Context): ConnectivityReceiver {
        return ConnectivityReceiver(context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    }
}