package com.examdple.gymus_android.core.di

import com.examdple.gymus_android.auth.data.dataSource.dataStore.AuthDataStore
import com.examdple.gymus_android.auth.data.dataSource.remote.UserApiService
import com.examdple.gymus_android.auth.data.dataSource.remote.UserApiServiceImpl
import com.examdple.gymus_android.auth.data.repository.UserRepositoryImpl
import com.examdple.gymus_android.auth.domain.repository.UserRepository
import com.examdple.gymus_android.core.database.GymusDatabase
import org.koin.dsl.module

val dataModule = module {
    single { get<GymusDatabase>().userDao() }
    single<UserApiService> { UserApiServiceImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single { AuthDataStore(get()) }
}