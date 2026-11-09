package com.example.gymusAndroid.dashboard.data.dataSource.remote

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto

interface DashboardApiService {
    suspend fun getActiveMembers(): ApiResponse<Int, ErrorResponseDto>
    suspend fun getTodaysRevenue(): ApiResponse<Double, ErrorResponseDto>
    suspend fun getRecentChecksInMembers(): ApiResponse<Set<RecentMemberChecksInDto>, ErrorResponseDto>
}