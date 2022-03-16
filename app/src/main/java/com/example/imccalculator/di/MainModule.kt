package com.example.imccalculator.di

import com.example.imccalculator.presentation.MainActivity
import com.example.imccalculator.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    scope<MainActivity> {
        viewModel { MainViewModel() }
    }
}