package com.xing.playandroid

import android.app.Application
import com.xing.playandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PlayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@PlayApplication)
            modules(appModule)
        }
    }

}