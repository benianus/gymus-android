package com.example.gymusAndroid.dashboard.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.core.dto.ApiResponse
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
        when (val result = activeMembersUseCase()) {
            is ApiResponse.Error -> {
                uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.error.message
                    )
                }
            }

            is ApiResponse.Success -> {
                uiState.update {
                    it.copy(
                        activeMembers = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }

    }

    suspend fun getRecentChecksInMembers() {
        when (val result = recentCheckInsUseCase()) {
            is ApiResponse.Error -> {
                Log.e("RECENT_CHECK_IN_ERROR", result.error.message)
                uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.error.message
                    )
                }
            }

            is ApiResponse.Success -> {
                Log.e("RECENT_CHECK_IN_SUCCESS", result.data.toString())
                uiState.update {
                    it.copy(
                        members = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    suspend fun getTodaysRevenue() {
        when (val result = todaysRevenueUseCase()) {
            is ApiResponse.Error -> {
                uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.error.message
                    )
                }
            }

            is ApiResponse.Success -> {
                uiState.update {
                    it.copy(
                        todayRevenue = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }
}