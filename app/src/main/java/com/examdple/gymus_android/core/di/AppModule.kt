package com.examdple.gymus_android.core.di

import com.examdple.gymus_android.core.config.createAuthDataStore
import com.examdple.gymus_android.core.config.createDatabase
import com.examdple.gymus_android.core.config.createHttpClient
import org.koin.dsl.module

val appModule = module {
    single { createDatabase(get()) }
    single { createHttpClient() }
    single { createAuthDataStore(get()) }
}