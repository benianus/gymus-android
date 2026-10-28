package com.example.gymusAndroid.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.dashboard.domain.useCases.ActiveMembersUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.RecentCheckInsUseCase
import com.example.gymusAndroid.dashboard.domain.useCases.TodaysRevenueUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val activeMembersUseCase: ActiveMembersUseCase,
    private val recentCheckInsUseCase: RecentCheckInsUseCase,
    private val todaysRevenueUseCase: TodaysRevenueUseCase,
    private val authDataStore: AuthDataStore
) : ViewModel() {
    val uiState: StateFlow<DashboardScreenState>
            field = MutableStateFlow(DashboardScreenState())
    var coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        uiState.update {
            it.copy(
                isLoading = false,
                error = throwable.message
            )
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            getRole()
            getActiveMembers()
            getRecentChecksInMembers()
            getTodaysRevenue()
        }
    }

    suspend fun getRole() {
        try {
            uiState.update {
                it.copy(
                    role = authDataStore.role.first(),
                    isLoading = false,
                    error = null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getActiveMembers() {
        try {
            uiState.update {
                it.copy(
                    activeMembers = activeMembersUseCase(),
                    isLoading = false,
                    error = null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getRecentChecksInMembers() {
        try {
            uiState.update {
                it.copy(
                    members = recentCheckInsUseCase(),
                    isLoading = false,
                    error = null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getTodaysRevenue() {
        try {
            uiState.update {
                it.copy(
                    todayRevenue = todaysRevenueUseCase(),
                    isLoading = false,
                    error = null
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}