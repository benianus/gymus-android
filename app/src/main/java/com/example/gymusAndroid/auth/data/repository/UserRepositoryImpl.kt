package com.example.gymusAndroid.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.local.UserDao
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiService
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LogoutRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.repository.UserRepository
import com.example.gymusAndroid.auth.toLoginDto
import com.example.gymusAndroid.auth.toRegisterDto
import com.example.gymusAndroid.core.dto.ApiResponse
import kotlinx.coroutines.flow.first

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val userDao: UserDao,
    private val dataStore: DataStore<Preferences>,
    private val authDataStore: AuthDataStore
) : UserRepository {
    override suspend fun login(user: User): ApiResponse<AuthResponseDto> {
        val data = userApiService.login(user.toLoginDto())
        return data
    }

    override suspend fun register(user: User): ApiResponse<AuthResponseDto> {
        return userApiService.register(user.toRegisterDto())
    }

    override suspend fun logout(): ApiResponse<String> {
        val data = userApiService.logout(
            LogoutRequestDto(
                username = authDataStore.username.first(),
                refreshToken = authDataStore.refreshToken.first()
            )
        )
        dataStore.edit {
            it.clear()
        }

        return data
    }
}