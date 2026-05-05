package com.examdple.gymus_android.auth.domain.useCases

import com.examdple.gymus_android.auth.domain.repository.UserRepository

class LogoutUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke() {
        userRepository.logout()
    }
}