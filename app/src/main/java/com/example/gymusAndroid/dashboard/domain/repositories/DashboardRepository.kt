package com.example.gymusAndroid.dashboard.domain.repositories

import com.example.gymusAndroid.dashboard.domain.models.Member

interface DashboardRepository {
    suspend fun getActiveMembers(): Int
    suspend fun getTodaysRevenue(): Int
    suspend fun getRecentCheckIns(): List<Member>
}