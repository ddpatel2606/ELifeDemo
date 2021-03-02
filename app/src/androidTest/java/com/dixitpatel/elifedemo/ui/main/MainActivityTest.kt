package com.dixitpatel.elifedemo.ui.main

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.dixitpatel.elifedemo.R
import com.dixitpatel.elifedemo.ui.splash.SplashScreenActivity
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mainActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
            MainActivity::class.java, false, true
    )

    @Test
    fun verifyMainActivityInjection() {
        ActivityScenario.launch(MainActivity::class.java).use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity { activity ->
                assertThat((activity as MainActivity).model).isNotNull()
                activity.model.taskListLiveData.observe(activity) { tasksList ->
                    assertThat(tasksList).isNotNull()
                }
            }
        }
    }

    @Test
    fun verifyMainActivityRecyclerViewVisible() {
        assertThat(mainActivityRule.activity.findViewById<View>(R.id.myRecyclerView).isVisible)
    }

    @Test
    fun verifyOfflineBannerVisible() {
        GlobalScope.launch(Dispatchers.Main)
        {
            mainActivityRule.activity.connectivityReceiver.observe(mainActivityRule.activity) { connected ->
                assertThat(connected).isTrue()
            }
        }
    }
}