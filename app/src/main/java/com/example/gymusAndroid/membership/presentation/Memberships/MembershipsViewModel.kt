package com.example.gymusAndroid.membership.presentation.Memberships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymusAndroid.membership.domain.useCases.FindAllMembersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MembershipsViewModel(private val findAllMembersUseCase: FindAllMembersUseCase) : ViewModel() {

    val uiState: StateFlow<MembershipsScreenState>
            field = MutableStateFlow(MembershipsScreenState())

    init {
        viewModelScope.launch {
            val members = findAllMembersUseCase(1, 10)
            uiState.update { it.copy(members = members) }
        }
    }
}