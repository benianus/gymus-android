package com.example.gymusAndroid.auth.presentation.register

import com.example.gymusAndroid.auth.domain.model.User

data class RegisterScreenState(
    var user: User? = null,
    var username: String = "",
    var usernameError: String? = null,
    var password: String = "",
    var passwordError: String? = null,
    var passwordConfirmation: String = "",
    var passwordConfirmationError: String? = null,
    var loading: Boolean = false,
    var error: String? = null
) {
    val isFormValid =
            username.isNotBlank() &&
            password.isNotBlank() &&
            passwordConfirmation.isNotBlank() &&
            usernameError == null &&
            passwordError == null &&
            passwordConfirmationError == null
}
