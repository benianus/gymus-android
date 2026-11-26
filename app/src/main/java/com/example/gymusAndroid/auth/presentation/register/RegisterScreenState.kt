package com.example.gymusAndroid.auth.presentation.register

import com.example.gymusAndroid.auth.domain.model.User

data class RegisterScreenState(
    val user: User? = null,
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordConfirmation: String = "",
    val passwordConfirmationError: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val isFormValid =
            username.isNotBlank() &&
            password.isNotBlank() &&
            passwordConfirmation.isNotBlank() &&
            usernameError == null &&
            passwordError == null &&
            passwordConfirmationError == null
}
