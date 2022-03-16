package com.example.imccalculator

import android.app.Application
import com.example.imccalculator.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(MainModule)
        }
    }
}