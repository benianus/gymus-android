package com.example.gymusAndroid.auth.data.dataSource.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val username: String,
    val role: String,
    val expiresIn: Long
)
