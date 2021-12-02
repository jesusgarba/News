package com.example.newapp.utils

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import android.content.Context


class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        appName: String?,
        context: Context?) : Application {
        return super.newApplication(cl, HiltTestApplication::class.java.getName(), context)
    }

}