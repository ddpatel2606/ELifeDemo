package com.dixitpatel.elifedemo.ui.splash

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.ui.main.MainActivity
import com.google.common.truth.Truth
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest
{

    @Rule
    @JvmField
    var splashActivityRule: ActivityTestRule<SplashScreenActivity> = ActivityTestRule(
        SplashScreenActivity::class.java, false, true
    )

    @Test
    fun verifySplashActivityInjection() {
        ActivityScenario.launch(SplashScreenActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->
                Truth.assertThat((activity as SplashScreenActivity).model).isNotNull()
            }
        }
    }

    @Test
    fun isSplashViewVisible() {
        Truth.assertThat(splashActivityRule.activity.findViewById<View>(R.id.animationView).isVisible)
    }
}