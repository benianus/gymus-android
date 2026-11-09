package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.core.config.createAuthDataStore
import com.example.gymusAndroid.core.config.createDatabase
import com.example.gymusAndroid.core.config.createHttpClient
import com.example.gymusAndroid.core.config.createImageLoader
import org.koin.dsl.module

val appModule = module {
    single { createDatabase(get()) }
    single { createHttpClient(get()) }
    single { createAuthDataStore(get()) }
    single { createImageLoader(get(), get()) }
}