package com.examdple.gymus_android.auth.data.dataSource.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val role: String,
    val expiresAt: Long
)
