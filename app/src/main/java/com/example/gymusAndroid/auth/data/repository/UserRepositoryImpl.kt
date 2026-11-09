package com.example.gymusAndroid.auth.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
import com.example.gymusAndroid.core.dto.ApiResponse.Error
import com.example.gymusAndroid.core.dto.ApiResponse.Success
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import kotlinx.coroutines.flow.first

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val userDao: UserDao,
    private val dataStore: DataStore<Preferences>,
    private val authDataStore: AuthDataStore
) : UserRepository {
    override suspend fun login(user: User): ApiResponse<AuthResponseDto, ErrorResponseDto> {
        val result = userApiService.login(user.toLoginDto())

        if (result is Success) {
            authDataStore.saveAuthData(result.data)
        }

        return result
    }

    override suspend fun register(user: User): ApiResponse<AuthResponseDto, ErrorResponseDto> {
        val result = userApiService.register(user.toRegisterDto())

        if (result is Success) {
            authDataStore.saveAuthData(result.data)
        }

        return result
    }

    override suspend fun logout(): ApiResponse<String, ErrorResponseDto> {
        val username = authDataStore.username.first()
        val refreshToken = authDataStore.refreshToken.first()
        val result = userApiService.logout(
            LogoutRequestDto(
                username = username,
                refreshToken = refreshToken
            )
        )
        Log.e("logout_repo", "logout repository success")
        when (result) {
            is Error -> authDataStore.clearAuthData()
            is Success -> authDataStore.clearAuthData()
        }

        return result
    }
}