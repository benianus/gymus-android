package com.example.gymusAndroid.auth.data.dataSource.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequestDto(val username: String, val refreshToken: String)