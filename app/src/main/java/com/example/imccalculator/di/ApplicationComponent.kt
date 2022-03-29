package com.example.imccalculator.di

import com.example.imccalculator.presentation.MainActivity
import dagger.Component


@Component(modules = arrayOf(MainModule::class))
interface ApplicationComponent {
    fun inject(activity: MainActivity)

}