package com.example.gymusAndroid.auth.domain.repository

import com.example.gymusAndroid.auth.domain.model.User

interface UserRepository {
    suspend fun login(user: User): String?
    suspend fun register(user: User): String?
    suspend fun logout()
}