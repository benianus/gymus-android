package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiService
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiServiceImpl
import com.example.gymusAndroid.auth.data.repository.UserRepositoryImpl
import com.example.gymusAndroid.auth.domain.repository.UserRepository
import com.example.gymusAndroid.core.database.GymusDatabase
import com.example.gymusAndroid.dashboard.data.dataSource.remote.DashboardApiService
import com.example.gymusAndroid.dashboard.data.dataSource.remote.DashboardApiServiceImpl
import com.example.gymusAndroid.dashboard.data.repositoriesImpl.DashboardRepositoryImpl
import com.example.gymusAndroid.dashboard.domain.repositories.DashboardRepository
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiServiceImpl
import com.example.gymusAndroid.membership.data.repositoriesImpl.FakeMembershipsRepositoryImpl
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val dataModule = module {
    single { get<GymusDatabase>().userDao() }
    single<AuthDataStore>()
    single<UserApiServiceImpl>() bind UserApiService::class
    single<UserRepositoryImpl>() bind UserRepository::class

    single<MembershipApiServiceImpl>() bind MembershipApiService::class
    single<FakeMembershipsRepositoryImpl>() bind MembershipRepository::class
//    single<MembershipRepositoryImpl>() bind MembershipRepository::class

    single<DashboardApiServiceImpl>() bind DashboardApiService::class
//    single<FakeDashboardRepositoryImpl>() bind DashboardRepository::class
    single<DashboardRepositoryImpl>() bind DashboardRepository::class
}