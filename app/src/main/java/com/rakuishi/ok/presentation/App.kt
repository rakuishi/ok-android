package com.rakuishi.ok.presentation

import android.app.Application
import com.rakuishi.ok.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}