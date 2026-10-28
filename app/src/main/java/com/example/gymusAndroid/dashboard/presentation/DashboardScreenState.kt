package com.example.gymusAndroid.dashboard.presentation

import com.example.gymusAndroid.dashboard.domain.models.Member

data class DashboardScreenState(
    val members: Set<Member> = emptySet(),
    val activeMembers: Int = 0,
    val todayRevenue: Double = 0.0,
    val role: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)