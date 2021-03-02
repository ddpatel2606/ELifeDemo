package com.dixitpatel.elifedemo.di.activitybuilder

import com.dixitpatel.elifedemo.ui.main.MainActivity
import com.dixitpatel.elifedemo.ui.main.MainActivityModule
import com.dixitpatel.elifedemo.ui.splash.SplashScreenActivity
import com.dixitpatel.elifedemo.ui.splash.SplashScreenActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This is Dagger Activity Builder Which activities will be used in app must be add
 * in Dagger Module.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeMainActivity(): MainActivity?

    @ContributesAndroidInjector(modules = [SplashScreenActivityModule::class])
    abstract fun contributeSplashActivity(): SplashScreenActivity?

}