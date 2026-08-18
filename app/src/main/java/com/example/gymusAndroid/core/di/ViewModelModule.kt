package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegisterViewModel(get()) }
}