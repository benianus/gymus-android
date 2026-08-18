package com.example.gymusAndroid.auth.data.dataSource.remote.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequestDto(
    val username: String,
    val password: String
)
