package com.example.gymusAndroid.dashboard.data.dataSource.remote

import com.example.gymusAndroid.core.dto.ApiResponse
import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto

interface DashboardApiService {
    suspend fun getActiveMembers(): ApiResponse<Int>
    suspend fun getTodaysRevenue(): ApiResponse<Double>
    suspend fun getRecentChecksInMembers(): ApiResponse<Set<RecentMemberChecksInDto>>
}