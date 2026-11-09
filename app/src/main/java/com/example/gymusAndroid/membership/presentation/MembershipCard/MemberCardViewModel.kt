package com.example.gymusAndroid.membership.presentation.MembershipCard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.navigation.MemberCardScreen
import com.example.gymusAndroid.membership.domain.useCases.FindMemberCardUseCase
import com.example.gymusAndroid.membership.domain.useCases.RecordAttendanceUseCase
import com.example.gymusAndroid.membership.domain.useCases.RenewMembershipUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MemberCardViewModel(
    private val findMemberCardUseCase: FindMemberCardUseCase,
    private val renewMembershipUseCase: RenewMembershipUseCase,
    private val recordAttendanceUseCase: RecordAttendanceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        uiState.update {
            it.copy(
                isLoading = false,
                error = throwable.message
            )
        }
    }
    val uiState: StateFlow<MemberCardScreenState>
            field = MutableStateFlow(MemberCardScreenState())

    init {
        val memberId = savedStateHandle.toRoute<MemberCardScreen>().memberId
        getMemberCard(memberId)
    }

    fun onErrorChanged() {
        uiState.update {
            it.copy(
                error = null
            )
        }
    }

    private fun getMemberCard(memberId: Int) {
        uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val result = findMemberCardUseCase(memberId)) {
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
                            memberCard = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }

    fun recordNewAttendance(memberId: Int) {
        uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val result = recordAttendanceUseCase(memberId)) {
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
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }

    fun renewMembership(memberId: Int) {
        uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            when (val result = renewMembershipUseCase(memberId)) {
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
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }
}