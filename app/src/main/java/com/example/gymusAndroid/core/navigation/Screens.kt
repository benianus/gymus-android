package com.example.gymusAndroid.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Tab

@Serializable
data object LoginScreen : Tab

@Serializable
data object RegisterScreen : Tab

@Serializable
data object DashboardScreen : Tab

@Serializable
data object MembershipsScreen : Tab

@Serializable
data object RegisterMembershipScreen : Tab

@Serializable
data object SessionsScreen : Tab

@Serializable
data object RegisterSessionScreen : Tab

@Serializable
data object ProductsScreen : Tab

@Serializable
data object ReportsScreen : Tab

@Serializable
data class MemberCardScreen(val memberId: Int) : Tab

