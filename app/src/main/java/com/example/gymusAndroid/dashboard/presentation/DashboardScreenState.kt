package com.example.gymusAndroid.dashboard.presentation

import com.example.gymusAndroid.dashboard.domain.models.Member

data class DashboardScreenState(
    val members: List<Member> = emptyList(),
    val activeMembers: Int = 0,
    val todayRevenue: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)