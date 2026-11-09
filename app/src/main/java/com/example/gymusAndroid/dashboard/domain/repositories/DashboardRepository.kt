package com.example.gymusAndroid.dashboard.domain.repositories

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.dashboard.domain.models.Member

interface DashboardRepository {
    suspend fun getActiveMembers(): ApiResponse<Int, ErrorResponseDto>
    suspend fun getTodaysRevenue(): ApiResponse<Double, ErrorResponseDto>
    suspend fun getRecentCheckIns(): ApiResponse<Set<Member>, ErrorResponseDto>
}