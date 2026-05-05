package com.examdple.gymus_android.auth.presentation

import com.examdple.gymus_android.auth.domain.model.User

data class AuthScreenState(
    var user: User? = null,
    var loading: Boolean = false,
    var error: String? = null
)
