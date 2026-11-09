package com.example.gymusAndroid.dashboard.data.repositoriesImpl

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.dashboard.data.dataSource.remote.DashboardApiService
import com.example.gymusAndroid.dashboard.domain.models.Member
import com.example.gymusAndroid.dashboard.domain.repositories.DashboardRepository
import com.example.gymusAndroid.dashboard.toModel

class DashboardRepositoryImpl(
    private val dashboardApiService: DashboardApiService
) : DashboardRepository {
    override suspend fun getActiveMembers(): ApiResponse<Int, ErrorResponseDto> {
        return dashboardApiService.getActiveMembers()
    }

    override suspend fun getTodaysRevenue(): ApiResponse<Double, ErrorResponseDto> {
        return dashboardApiService.getTodaysRevenue()
    }

    override suspend fun getRecentCheckIns(): ApiResponse<Set<Member>, ErrorResponseDto> =
            when (val result = dashboardApiService.getRecentChecksInMembers()) {
                is ApiResponse.Error -> {
                    ApiResponse.Error(ErrorResponseDto(result.error.message))
                }

                is ApiResponse.Success -> {
                    ApiResponse.Success(result.data.map {
                        it.toModel()
                    }.toSet())
                }
            }
}