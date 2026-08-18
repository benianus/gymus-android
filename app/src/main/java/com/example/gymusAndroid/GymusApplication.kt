package com.example.gymusAndroid

import android.app.Application
import com.example.gymusAndroid.core.di.appModule
import com.example.gymusAndroid.core.di.dataModule
import com.example.gymusAndroid.core.di.domainModule
import com.example.gymusAndroid.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GymusApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GymusApplication)
            modules(appModule, dataModule, domainModule, viewModelModule)
        }
    }
}