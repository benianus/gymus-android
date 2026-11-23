package com.example.gymusAndroid.auth.data.dataSource.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    val username: String,
    val role: String,
    @SerialName("expires_in")
    val expiresIn: Long
)
