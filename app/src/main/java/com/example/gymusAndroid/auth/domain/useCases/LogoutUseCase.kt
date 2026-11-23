package com.example.gymusAndroid.auth.domain.useCases

import android.util.Log
import com.example.core.dto.ApiResponse
import com.example.core.dto.ErrorResponseDto
import com.example.gymusAndroid.auth.domain.repository.UserRepository

class LogoutUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): ApiResponse<String, ErrorResponseDto> {
        Log.i("logout_use_case", "logout_done")
        return userRepository.logout()
    }
}