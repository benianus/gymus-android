package com.example.gymusAndroid.membership.presentation.MembershipCard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.gymusAndroid.core.navigation.MemberCardScreen
import com.example.gymusAndroid.membership.domain.useCases.FindMemberCardUseCase
import com.example.gymusAndroid.membership.domain.useCases.RecordAttendanceUseCase
import com.example.gymusAndroid.membership.domain.useCases.RenewMembershipUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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
                loading = false,
                error = throwable.message
            )
        }
    }
    var uiState = MutableStateFlow(MemberCardScreenState())
        private set

    init {
        val memberId = savedStateHandle.toRoute<MemberCardScreen>().memberId
        getMemberCard(memberId)
    }

    private fun getMemberCard(memberId: Int) {
        uiState.update { it.copy(loading = true) }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            try {
                val memberCard = findMemberCardUseCase(memberId)
                uiState.update {
                    it.copy(
                        memberCard = memberCard,
                        loading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState.update {
                    it.copy(
                        loading = false,
                        error = e.message
                    )
                }
                throw e
            }
        }
    }

    fun recordNewAttendance(memberId: Int) {}

    fun renewMembership(memberId: Int) {}
}