package com.example.gymusAndroid.auth.data.dataSource.remote

import android.util.Log
import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RefreshTokenRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RegisterRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.core.BASE_URL
import com.example.gymusAndroid.core.dto.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.clearAuthTokens
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class UserApiServiceImpl(
    private val httpClient: HttpClient,
    private val authDataStore: AuthDataStore
) : UserApiService {
    override suspend fun login(loginRequestDto: LoginRequestDto): String? {
        try {
            val response = httpClient.post("${BASE_URL}/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequestDto)
            }

            return if (response.status == HttpStatusCode.OK) {
                val data = response.body<ApiResponse<AuthResponseDto>>().data
                Log.e("DATA", data.toString())
                response.body<ApiResponse<AuthResponseDto>>().data?.let {
                    authDataStore.saveAuthData(it)
                }
                null
            } else {
                "Bad credentials"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Bad credentials"
        }
    }

    override suspend fun register(registerRequestDto: RegisterRequestDto): String? {
        try {
            val response =
                    httpClient.post("${BASE_URL}/auth/register") {
                        contentType(ContentType.Application.Json)
                        setBody(registerRequestDto)
                    }
            return when (response.status) {
                HttpStatusCode.OK -> {
                    response.body<ApiResponse<AuthResponseDto>>().data?.let {
                        authDataStore.saveAuthData(it)
                    }
                    null
                }

                else -> {
                    val errors = response.body<ApiResponse<Any>>().errors
                    Log.i("LOGIN_STATUS", errors.toString())
                    "Registration failed"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return "Registration failed"
        }
    }

    override suspend fun logout(logoutRefreshTokenRequestDto: RefreshTokenRequestDto) {
        httpClient.clearAuthTokens()
        authDataStore.clearAuthData()
    }
}