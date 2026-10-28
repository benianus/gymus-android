package com.example.gymusAndroid.auth.presentation.login

import com.example.gymusAndroid.auth.domain.model.User

data class LoginScreenState(
    val user: User? = null,
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val loading: Boolean = false,
    val errors: List<String>? = null
) {
    val isFormValid: Boolean =
            password.isNotBlank() &&
            username.isNotBlank() &&
            usernameError == null &&
            passwordError == null
}
