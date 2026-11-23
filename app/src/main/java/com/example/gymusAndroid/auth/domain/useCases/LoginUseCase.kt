package com.example.gymusAndroid.auth.domain.useCases

import com.example.core.dto.ApiResponse
import com.example.core.dto.ErrorResponseDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): ApiResponse<AuthResponseDto, ErrorResponseDto> {
        return userRepository.login(user)
    }
}