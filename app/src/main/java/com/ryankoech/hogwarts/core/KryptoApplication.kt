package com.ryankoech.hogwarts.core

import android.app.Application
import com.ryankoech.hogwarts.core.logging.CrashAndLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HogwartsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        CrashAndLog.setupTimber()
    }

}