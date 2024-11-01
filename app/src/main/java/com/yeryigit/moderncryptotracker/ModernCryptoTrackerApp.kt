package com.yeryigit.moderncryptotracker

import android.app.Application
import com.yeryigit.moderncryptotracker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ModernCryptoTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ModernCryptoTrackerApp)
            androidLogger()
            modules(appModule)
        }
    }
}