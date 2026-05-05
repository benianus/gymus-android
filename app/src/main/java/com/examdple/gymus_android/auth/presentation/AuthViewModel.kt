package com.examdple.gymus_android.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examdple.gymus_android.auth.data.dataSource.dataStore.AuthDataStore
import com.examdple.gymus_android.auth.domain.model.User
import com.examdple.gymus_android.auth.domain.useCases.LoginUseCase
import com.examdple.gymus_android.auth.domain.useCases.LogoutUseCase
import com.examdple.gymus_android.auth.domain.useCases.RegisterUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val authDataStore: AuthDataStore,
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        AuthScreenState()
    )
    val uiState = _uiState.asStateFlow()
    val authenticated = authDataStore.accessToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val coroutineExceptionHandler =
        CoroutineExceptionHandler { context, throwable ->
            _uiState.update {
                it.copy(
                    loading = false,
                    error = throwable.message
                )
            }
        }

    fun login(username: String, password: String) {
        _uiState.update {
            it.copy(
                loading = true
            )
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            loginUseCase(
                User(
                    id = 0,
                    username = username,
                    password = password,
                    role = "OWNER",
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now()
                )
            )
        }
    }

    suspend fun register(username: String, password: String) {

    }

    suspend fun logout(username: String) {

    }
}