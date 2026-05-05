package com.examdple.gymus_android.auth.data.dataSource.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val username: String,
    val password: String
)
