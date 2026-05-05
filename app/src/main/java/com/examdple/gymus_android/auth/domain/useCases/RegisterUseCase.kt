package com.examdple.gymus_android.auth.domain.useCases

import com.examdple.gymus_android.auth.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    operator fun invoke() {}
}