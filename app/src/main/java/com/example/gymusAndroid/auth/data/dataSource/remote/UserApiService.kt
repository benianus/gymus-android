package com.example.gymusAndroid.auth.data.dataSource.remote

import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RefreshTokenRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RegisterRequestDto

interface UserApiService {
    suspend fun login(loginRequestDto: LoginRequestDto): String?
    suspend fun register(registerRequestDto: RegisterRequestDto): String?
    suspend fun logout(logoutRefreshTokenRequestDto: RefreshTokenRequestDto)
}