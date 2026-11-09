package com.example.gymusAndroid.membership.presentation.Memberships

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.membership.domain.useCases.FindAllMembersUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MembershipsViewModel(
    private val findAllMembersUseCase: FindAllMembersUseCase
) : ViewModel() {

    val uiState: StateFlow<MembershipsScreenState>
            field = MutableStateFlow(MembershipsScreenState())
    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
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
            when (val result = findAllMembersUseCase(1, 10)) {
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
                            members = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            }
        }
    }
}