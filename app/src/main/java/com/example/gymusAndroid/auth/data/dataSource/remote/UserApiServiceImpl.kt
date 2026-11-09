package com.example.gymusAndroid.auth.data.dataSource.remote

import android.util.Log
import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LogoutRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RegisterRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ApiResponse.Error
import com.example.gymusAndroid.core.dto.ApiResponse.Success
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.core.helpers.BASE_URL
import com.example.gymusAndroid.core.helpers.apiResponse
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
            val response = httpClient.post("${BASE_URL}/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(loginRequestDto)
            }
            apiResponse(response)
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

            apiResponse(response)
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
        Log.i("logout_api_service", "logout succeed")
        return try {
            val response = httpClient.post("${BASE_URL}/auth/logout") {
                contentType(ContentType.Application.Json)
                setBody(logoutRequestDto)
            }

            when (response.status) {
                HttpStatusCode.NoContent -> {
                    Log.i(
                        "logout_api_service_success",
                        "logout succeed api service success"
                    )
                    httpClient.clearAuthTokens()
                    Success(response.body<Success<String, Nothing>>().data)
                }

                else -> {
                    Log.i(
                        "logout_api_service_error",
                        "logout succeed api service error"
                    )
                    Error(response.body<Error<Nothing, ErrorResponseDto>>().error)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error(ErrorResponseDto("Logout Succeed!"))
        }
    }
}

