package com.example.core.di

import com.example.core.config.createAuthDataStore
import com.example.core.config.createDatabase
import com.example.core.config.createHttpClient
import com.example.core.config.createImageLoader
import org.koin.dsl.module

val appModule = module {
    single { createDatabase(get()) }
    single { createHttpClient(get(), get()) }
    single { createAuthDataStore(get()) }
    single {
        createImageLoader(
            get(),
            get()
        )
    }
}