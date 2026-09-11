package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.auth.presentation.register.RegisterViewModel
import com.example.gymusAndroid.membership.presentation.Memberships.MembershipsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { MembershipsViewModel(get()) }
}