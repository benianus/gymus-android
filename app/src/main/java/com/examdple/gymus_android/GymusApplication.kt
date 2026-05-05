package com.examdple.gymus_android

import android.app.Application
import com.examdple.gymus_android.core.di.appModule
import com.examdple.gymus_android.core.di.dataModule
import com.examdple.gymus_android.core.di.domainModule
import com.examdple.gymus_android.core.di.viewModelModule
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