package com.example.gymusAndroid.core.di

import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.auth.presentation.register.RegisterViewModel
import com.example.gymusAndroid.dashboard.presentation.DashboardViewModel
import com.example.gymusAndroid.membership.presentation.MembershipCard.MemberCardViewModel
import com.example.gymusAndroid.membership.presentation.Memberships.MembershipsViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val viewModelModule = module {
    viewModel<LoginViewModel>()
    viewModel<RegisterViewModel>()
    viewModel<MembershipsViewModel>()
    viewModel<MemberCardViewModel>()
    viewModel<DashboardViewModel>()
}