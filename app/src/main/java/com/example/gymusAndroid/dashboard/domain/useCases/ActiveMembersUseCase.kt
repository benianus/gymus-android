package com.example.gymusAndroid.dashboard.domain.useCases

import com.example.gymusAndroid.dashboard.domain.repositories.DashboardRepository

class ActiveMembersUseCase(private val dashboardRepository: DashboardRepository) {
    suspend operator fun invoke(): Int = dashboardRepository.getActiveMembers()
}