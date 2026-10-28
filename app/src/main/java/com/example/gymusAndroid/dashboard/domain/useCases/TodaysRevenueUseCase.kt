package com.example.gymusAndroid.dashboard.domain.useCases

import com.example.gymusAndroid.dashboard.domain.repositories.DashboardRepository

class TodaysRevenueUseCase(private val dashboardRepository: DashboardRepository) {
    suspend operator fun invoke() = dashboardRepository.getTodaysRevenue()
}