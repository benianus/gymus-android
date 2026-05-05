package com.examdple.gymus_android.core.dto

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val data: T?,
    val status: String,
    val errors: List<String>?
)
