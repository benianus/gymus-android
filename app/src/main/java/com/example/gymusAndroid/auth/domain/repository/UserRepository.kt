package com.example.gymusAndroid.auth.domain.repository

import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto

interface UserRepository {
    suspend fun login(user: User): ApiResponse<AuthResponseDto, ErrorResponseDto>
    suspend fun register(user: User): ApiResponse<AuthResponseDto, ErrorResponseDto>
    suspend fun logout(): ApiResponse<String, ErrorResponseDto>
}