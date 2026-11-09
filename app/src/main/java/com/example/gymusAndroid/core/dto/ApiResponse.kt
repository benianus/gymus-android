package com.example.gymusAndroid.core.dto

import kotlinx.serialization.Serializable

@Serializable
sealed interface ApiResponse<out D, out E> {

    @Serializable
    data class Success<out D, out E>(val data: D) : ApiResponse<D, E>

    @Serializable
    data class Error<out D, out E>(val error: E) : ApiResponse<D, E>
}
