package com.dixitpatel.elifedemo.ui.splash

import android.content.Intent
import android.os.Bundle
import com.dixitpatel.elifedemo.ui.base.BaseActivity
import com.dixitpatel.elifedemo.ui.main.MainActivity
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.databinding.ActivitySplashBinding
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 *  Splash Activity Class : Display lottie animation.
 */
class SplashScreenActivity : BaseActivity<SplashScreenViewModel?>() {

    @Inject
    lateinit var model : SplashScreenViewModel

    private val binding: ActivitySplashBinding by binding(R.layout.activity_splash)

    private val viewModelJob = SupervisorJob()

    private val uiScope = CoroutineScope(Dispatchers.Default + viewModelJob)

    override fun getViewModel(): SplashScreenViewModel {
        return model
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.animationView.playAnimation()

        uiScope.launch {
            delay(5000)
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }
    }

    // When on Destroy method call cancel coroutine to avoid Memory leak errors.
    override fun onDestroy() {
        super.onDestroy()
        if(viewModelJob.isActive) {
            viewModelJob.cancel()
        }
    }

}