package com.example.gymusAndroid.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.useCases.LoginUseCase
import com.example.gymusAndroid.auth.domain.useCases.LogoutUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
    authDataStore: AuthDataStore,
) : ViewModel() {
    val uiState: StateFlow<LoginScreenState>
            field = MutableStateFlow(LoginScreenState())
    val authenticated = authDataStore.accessToken.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, throwable ->
                uiState.update {
                    it.copy(
                        loading = false,
                        errors = listOf(throwable.message ?: "Unknown error")
                    )
                }
            }

    fun onUsernameChanged(username: String) {
        when {
            username.isBlank() -> {
                uiState.update {
                    it.copy(
                        username = username,
                        usernameError = "username is required"
                    )
                }
            }

            else -> {
                uiState.update {
                    it.copy(
                        username = username,
                        usernameError = null
                    )
                }
            }
        }
    }

    fun onPasswordChanged(password: String) {
        when {
            password.isBlank() -> {
                uiState.update {
                    it.copy(
                        password = password,
                        passwordError = "password is required"
                    )
                }
            }

            else -> {
                uiState.update {
                    it.copy(
                        password = password,
                        passwordError = null
                    )
                }
            }
        }
    }

    fun login(username: String, password: String) {
        uiState.update { it.copy(loading = true) }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = loginUseCase(
                User(
                    id = 0,
                    username = username,
                    password = password,
                    role = "OWNER",
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now()
                )
            )

            if (result.data != null) {
                uiState.update { it.copy(loading = false) }
            } else {
                uiState.update {
                    it.copy(
                        loading = false,
                        errors = result.errors
                    )
                }
            }
        }

    }

    fun logout() {

        uiState.update {
            it.copy(
                loading = true,
            )
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            logoutUseCase()
        }
    }
}


