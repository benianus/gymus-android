package com.examdple.gymus_android.auth.domain.repository

import com.examdple.gymus_android.auth.domain.model.User

interface UserRepository {
    suspend fun login(user: User)
    suspend fun register()
    suspend fun logout()
}