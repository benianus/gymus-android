package com.example.gymusAndroid.auth.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.dto.ApiResponse
import com.example.core.dto.Error
import com.example.core.dto.ErrorResponseDto
import com.example.core.dto.Success
import com.example.gymusAndroid.auth.data.dataSource.local.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.local.roomDb.UserDao
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiService
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LogoutRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.repository.UserRepository
import com.example.gymusAndroid.auth.toLoginDto
import com.example.gymusAndroid.auth.toRegisterDto
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
            Log.i("auth_data", result.data.toString())
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
        Log.i("logout_repo", "logout_done")
        val username = authDataStore.username.first()
        val refreshToken = authDataStore.refreshToken.first()

        val result = userApiService.logout(
            LogoutRequestDto(
                username = username,
                refreshToken = refreshToken
            )
        )
        when (result) {
            is Error -> authDataStore.clearAuthData()
            is Success -> authDataStore.clearAuthData()
        }

        return result
    }
}