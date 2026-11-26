package com.example.gymusAndroid.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dto.Error
import com.example.core.dto.Success
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.useCases.RegisterUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModel
import kotlin.time.Clock

@KoinViewModel
class RegisterViewModel(private val registerUseCase: RegisterUseCase) :
    ViewModel() {
    val uiState: StateFlow<RegisterScreenState>
            field = MutableStateFlow(RegisterScreenState())
    val coroutineExceptionHandler =
            CoroutineExceptionHandler { _, throwable ->
                uiState.update {
                    it.copy(
                        isLoading = false,
                        error = throwable.message
                    )
                }
            }

    fun onErrorChanged() {
        uiState.update {
            it.copy(
                error = null
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

            username.length < 3 -> {
                uiState.update {
                    it.copy(
                        username = username,
                        usernameError = "username should not be less then 3 characters"
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
            password.isBlank() || password.isEmpty() -> {
                uiState.update {
                    it.copy(
                        password = password,
                        passwordError = "password is required"
                    )
                }
            }

            password.length < 8 -> {
                uiState.update {
                    it.copy(
                        password = password,
                        passwordError = "password should not be less then 8 characters"
                    )
                }
            }

            password != uiState.value.passwordConfirmation -> {
                uiState.update {
                    it.copy(
                        password = password,
                        passwordError = "password not match"
                    )
                }
            }

            else -> {
                uiState.update {
                    it.copy(
                        password = password,
                        passwordError = null,
                        passwordConfirmationError = null
                    )
                }
            }
        }
    }

    fun onPasswordConfirmationChanged(passwordConfirmation: String) {
        when {
            passwordConfirmation.isEmpty() || passwordConfirmation.isBlank() -> {
                uiState.update {
                    it.copy(
                        passwordConfirmation = passwordConfirmation,
                        passwordConfirmationError = "password confirmation is required"
                    )
                }
            }

            passwordConfirmation != uiState.value.password -> {
                uiState.update {
                    it.copy(
                        passwordConfirmation = passwordConfirmation,
                        passwordConfirmationError = "password not matched"
                    )
                }
            }

            else -> {
                uiState.update {
                    it.copy(
                        passwordConfirmation = passwordConfirmation,
                        passwordConfirmationError = null,
                        passwordError = null
                    )
                }
            }
        }
    }

    fun register(username: String, password: String) {
        uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val result = registerUseCase(
                User(
                    id = 0,
                    username = username,
                    password = password,
                    role = "OWNER",
                    createdAt = Clock.System.now(),
                    updatedAt = Clock.System.now()
                )
            )

            when (result) {
                is Error -> {
                    uiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.error.message
                        )
                    }
                }

                is Success -> {
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