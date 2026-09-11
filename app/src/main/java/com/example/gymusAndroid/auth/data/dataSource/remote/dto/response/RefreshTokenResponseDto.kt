package com.example.gymusAndroid.auth.data.dataSource.remote.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponseDto(val accessToken: String, val refreshToken: String)
