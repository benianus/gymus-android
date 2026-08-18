package com.example.gymusAndroid.core.dto

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse<T>(
    val data: T?,
    val errors: List<String>?,
    val status: String,
)

