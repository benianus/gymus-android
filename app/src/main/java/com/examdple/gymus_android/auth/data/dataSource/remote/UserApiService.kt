package com.examdple.gymus_android.auth.data.dataSource.remote

import com.examdple.gymus_android.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.examdple.gymus_android.auth.data.dataSource.remote.dto.request.RegisterRequestDto

interface UserApiService {
    suspend fun login(loginRequestDto: LoginRequestDto)
    suspend fun register(registerRequestDto: RegisterRequestDto)
}