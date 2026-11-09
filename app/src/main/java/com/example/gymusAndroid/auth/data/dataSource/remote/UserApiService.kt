package com.example.gymusAndroid.auth.data.dataSource.remote

import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LogoutRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RegisterRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto

interface UserApiService {
    suspend fun login(loginRequestDto: LoginRequestDto): ApiResponse<AuthResponseDto, ErrorResponseDto>
    suspend fun register(registerRequestDto: RegisterRequestDto): ApiResponse<AuthResponseDto, ErrorResponseDto>
    suspend fun logout(logoutRequestDto: LogoutRequestDto): ApiResponse<String, ErrorResponseDto>
}