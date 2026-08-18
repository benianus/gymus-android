package com.example.gymusAndroid.auth.domain.useCases

import com.example.gymusAndroid.auth.domain.repository.UserRepository

class LogoutUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() {
        userRepository.logout()
    }
}