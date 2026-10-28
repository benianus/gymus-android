package com.example.gymusAndroid.dashboard.data.repositoriesImpl

import com.example.gymusAndroid.dashboard.data.dataSource.remote.DashboardApiService
import com.example.gymusAndroid.dashboard.domain.models.Member
import com.example.gymusAndroid.dashboard.domain.repositories.DashboardRepository
import com.example.gymusAndroid.dashboard.toModel

class DashboardRepositoryImpl(
    private val dashboardApiService: DashboardApiService
) : DashboardRepository {
    override suspend fun getActiveMembers(): Int {
        return dashboardApiService.getActiveMembers().data ?: 0
    }

    override suspend fun getTodaysRevenue(): Double {
        return dashboardApiService.getTodaysRevenue().data ?: 0.0
    }

    override suspend fun getRecentCheckIns(): Set<Member> {
        return dashboardApiService.getRecentChecksInMembers()
                   .data
                   ?.map { it.toModel() }
                   ?.toSet() ?: emptySet()
    }
}