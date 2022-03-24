package com.example.imccalculator

import android.app.Application
import com.example.imccalculator.di.DaggerApplicationComponent

class MyApplication : Application() {
    val appcomponent = DaggerApplicationComponent.create()
}