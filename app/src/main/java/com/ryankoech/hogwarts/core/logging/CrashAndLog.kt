package com.ryankoech.hogwarts.core.logging

import com.ryankoech.hogwarts.BuildConfig
import com.ryankoech.krypto.core.logging.CrashReportingTree
import timber.log.Timber

object CrashAndLog {
    fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return "Timber: (${element.fileName}:${element.lineNumber})#${element.methodName}"
                }
            })
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}