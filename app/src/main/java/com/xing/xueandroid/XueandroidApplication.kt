package com.xing.xueandroid

import android.app.Application
import com.xing.xueandroid.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class XueandroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@XueandroidApplication)
            modules(appModule)
        }
    }

}