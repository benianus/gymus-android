package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiService
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiServiceImpl
import com.example.gymusAndroid.auth.data.repository.UserRepositoryImpl
import com.example.gymusAndroid.auth.domain.repository.UserRepository
import com.example.gymusAndroid.core.database.GymusDatabase
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiServiceImpl
import com.example.gymusAndroid.membership.data.repositoriesImpl.FakeMembershipsRepository
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository
import org.koin.dsl.module

val dataModule = module {
    single { get<GymusDatabase>().userDao() }
    single { AuthDataStore(get()) }
    single<UserApiService> { UserApiServiceImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }

    single<MembershipApiService> { MembershipApiServiceImpl(get()) }
    single<MembershipRepository> { FakeMembershipsRepository() }
//    single<MembershipRepository> { MembershipRepositoryImpl(get()) }
}