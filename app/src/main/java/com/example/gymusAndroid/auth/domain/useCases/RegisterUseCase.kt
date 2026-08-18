package com.example.gymusAndroid.auth.domain.useCases

import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.repository.UserRepository

class RegisterUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User): String? = userRepository.register(user)
}