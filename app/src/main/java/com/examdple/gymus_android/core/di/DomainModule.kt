package com.examdple.gymus_android.core.di

import com.examdple.gymus_android.auth.domain.useCases.LoginUseCase
import com.examdple.gymus_android.auth.domain.useCases.LogoutUseCase
import com.examdple.gymus_android.auth.domain.useCases.RegisterUseCase
import org.koin.dsl.module

val domainModule = module {
    single { LoginUseCase(get()) }
    single { RegisterUseCase(get()) }
    single { LogoutUseCase(get()) }
}