package com.example.gymusAndroid.membership.presentation.MembershipCard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.gymusAndroid.core.navigation.MemberCard
import kotlinx.coroutines.launch

class MemberCardViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    var uiState by mutableStateOf(MemberCardScreenState())
        private set

    init {
        viewModelScope.launch {
            val memberId = savedStateHandle.toRoute<MemberCard>().memberId
            val memberCard = getMemberCard(memberId)
        }
    }

    private suspend fun getMemberCard(memberId: Int) {
    }
}