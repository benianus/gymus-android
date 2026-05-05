package com.examdple.gymus_android.auth.domain.useCases

import com.examdple.gymus_android.auth.domain.model.User
import com.examdple.gymus_android.auth.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) {
        userRepository.login(user)
    }
}