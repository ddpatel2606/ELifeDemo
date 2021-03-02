package com.dixitpatel.elifedemo.application

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import org.junit.Assert.*

@Suppress("unused")
class MyApplicationTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
            cl: ClassLoader?,
            className: String?,
            context: Context?
    ): Application = super.newApplication(cl, MyApplication::class.java.name, context)
}
