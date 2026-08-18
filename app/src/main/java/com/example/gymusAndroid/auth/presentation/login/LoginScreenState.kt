package com.example.gymusAndroid.auth.presentation.login

import com.example.gymusAndroid.auth.domain.model.User

data class LoginScreenState(
    var user: User? = null,
    var username: String = "",
    var usernameError: String? = null,
    var password: String = "",
    var passwordError: String? = null,
    var loading: Boolean = false,
    var error: String? = null
) {
    val isFormValid: Boolean =
            password.isNotBlank() &&
            username.isNotBlank() &&
            usernameError == null &&
            passwordError == null
}
