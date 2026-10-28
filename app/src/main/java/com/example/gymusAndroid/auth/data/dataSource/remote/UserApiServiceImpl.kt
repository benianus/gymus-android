package com.example.gymusAndroid.auth.data.dataSource.remote

import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LogoutRequestDto
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
    override suspend fun login(loginRequestDto: LoginRequestDto): ApiResponse<AuthResponseDto> {
        try {
            val response = httpClient.post("${BASE_URL}/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequestDto)
            }

            return when (response.status) {
                HttpStatusCode.OK -> {
                    response.body()
                }

                else -> {
                    response.body()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse(
                data = null,
                errors = listOf(e.message.toString()),
                status = "error"
            )
        }
    }

    override suspend fun register(registerRequestDto: RegisterRequestDto): ApiResponse<AuthResponseDto> {
        try {
            val response =
                    httpClient.post("${BASE_URL}/auth/register") {
                        contentType(ContentType.Application.Json)
                        setBody(registerRequestDto)
                    }
            return when (response.status) {
                HttpStatusCode.OK -> {
                    response.body()
                }

                else -> {
                    response.body()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse(
                data = null,
                errors = listOf(e.message.toString()),
                status = "error"
            )
        }
    }

    override suspend fun logout(logoutRequestDto: LogoutRequestDto): ApiResponse<String> {
        return try {
            val response = httpClient.post("${BASE_URL}/auth/register") {
                contentType(ContentType.Application.Json)
                setBody(logoutRequestDto)
            }

            when (response.status) {
                HttpStatusCode.OK -> {
                    httpClient.clearAuthTokens()
                    authDataStore.clearAuthData()
                    response.body()
                }

                else -> {
                    response.body()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse(
                data = null,
                errors = listOf(e.message.toString()),
                status = "error"
            )
        }
    }
}