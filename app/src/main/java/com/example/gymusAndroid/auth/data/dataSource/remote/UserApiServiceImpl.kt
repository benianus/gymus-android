package com.example.gymusAndroid.auth.data.dataSource.remote

import android.util.Log
import com.example.core.dto.ApiResponse
import com.example.core.dto.Error
import com.example.core.dto.ErrorResponseDto
import com.example.core.dto.Success
import com.example.core.helpers.BASE_URL
import com.example.core.helpers.apiResponse
import com.example.gymusAndroid.auth.data.dataSource.local.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LogoutRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RegisterRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
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
    override suspend fun login(
        loginRequestDto: LoginRequestDto
    ): ApiResponse<AuthResponseDto, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.post("${BASE_URL}/auth/login") {
                        contentType(ContentType.Application.Json)
                        setBody(loginRequestDto)
                    }
            apiResponse<AuthResponseDto, ErrorResponseDto>(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                error = ErrorResponseDto(
                    message = e.message ?: "Unknown error!"
                )
            )
        }
    }

    override suspend fun register(
        registerRequestDto: RegisterRequestDto
    ): ApiResponse<AuthResponseDto, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.post("${BASE_URL}/auth/register") {
                        contentType(ContentType.Application.Json)
                        setBody(registerRequestDto)
                    }

            apiResponse<AuthResponseDto, ErrorResponseDto>(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "Unknown error"
                )
            )
        }
    }

    override suspend fun logout(
        logoutRequestDto: LogoutRequestDto
    ): ApiResponse<String, ErrorResponseDto> {
        Log.i("logout_api", "logout_done")
        return try {
            val response =
                    httpClient.post("${BASE_URL}/auth/logout") {
                        contentType(ContentType.Application.Json)
                        setBody(logoutRequestDto)
                    }

            when (response.status) {
                HttpStatusCode.NoContent -> {
                    httpClient.clearAuthTokens()
                    Success(response.body<Success<String, Nothing>>().data)
                }

                else -> {
                    httpClient.clearAuthTokens()
                    Error(response.body<Error<Nothing, ErrorResponseDto>>().error)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error(ErrorResponseDto("Logout Succeed!"))
        }
    }
}

