package com.example.core.di

import androidx.paging.PagingSource
import com.example.gymusAndroid.auth.domain.useCases.LoginUseCase
import com.example.gymusAndroid.auth.domain.useCases.LogoutUseCase
import com.example.gymusAndroid.auth.domain.useCases.RegisterUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.ActiveMembersUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.RecentCheckInsUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.TodaysRevenueUseCase
import com.example.gymusAndroid.membership.data.repositoriesImpl.MembershipPagingSource
import com.example.gymusAndroid.membership.domain.useCases.DeleteMembershipUseCase
import com.example.gymusAndroid.membership.domain.useCases.FindAllMembersUseCase
import com.example.gymusAndroid.membership.domain.useCases.FindMemberCardUseCase
import com.example.gymusAndroid.membership.domain.useCases.RecordAttendanceUseCase
import com.example.gymusAndroid.membership.domain.useCases.RenewMembershipUseCase
import com.example.gymusAndroid.membership.domain.useCases.UpdateMembershipUseCase
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val domainModule = module {
    single<LoginUseCase>()
    single<RegisterUseCase>()
    single<LogoutUseCase>()

    single<DeleteMembershipUseCase>()
    single<FindAllMembersUseCase>()
    single<FindMemberCardUseCase>()
    single<RecordAttendanceUseCase>()
    single<RenewMembershipUseCase>()
    single<RenewMembershipUseCase>()
    single<UpdateMembershipUseCase>()

    single<ActiveMembersUseCase>()
    single<TodaysRevenueUseCase>()
    single<RecentCheckInsUseCase>()

    single<MembershipPagingSource>() bind PagingSource::class
}