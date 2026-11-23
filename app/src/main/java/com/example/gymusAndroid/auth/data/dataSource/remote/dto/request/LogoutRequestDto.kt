package com.example.gymusAndroid.auth.data.dataSource.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequestDto(
    val username: String,
    @SerialName("refresh_token")
    val refreshToken: String
)