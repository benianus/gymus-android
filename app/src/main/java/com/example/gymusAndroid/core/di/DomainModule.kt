package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.domain.useCases.LoginUseCase
import com.example.gymusAndroid.auth.domain.useCases.LogoutUseCase
import com.example.gymusAndroid.auth.domain.useCases.RegisterUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.ActiveMembersUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.RecentCheckInsUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.TodaysRevenueUseCase
import com.example.gymusAndroid.membership.domain.useCases.FindAllMembersUseCase
import com.example.gymusAndroid.membership.domain.useCases.FindMemberCardUseCase
import com.example.gymusAndroid.membership.domain.useCases.RecordAttendanceUseCase
import com.example.gymusAndroid.membership.domain.useCases.RenewMembershipUseCase
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val domainModule = module {
    single<LoginUseCase>()
    single<RegisterUseCase>()
    single<LogoutUseCase>()

    single<FindAllMembersUseCase>()
    single<FindMemberCardUseCase>()
    single<RenewMembershipUseCase>()
    single<RecordAttendanceUseCase>()

    single<ActiveMembersUseCase>()
    single<TodaysRevenueUseCase>()
    single<RecentCheckInsUseCase>()
}