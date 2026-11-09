package com.example.gymusAndroid.auth.domain.useCases

import com.example.gymusAndroid.auth.domain.repository.UserRepository
import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto

class LogoutUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): ApiResponse<String, ErrorResponseDto> {
        return userRepository.logout()
    }
}