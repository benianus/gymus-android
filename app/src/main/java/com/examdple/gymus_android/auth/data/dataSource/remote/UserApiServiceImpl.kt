package com.examdple.gymus_android.auth.data.dataSource.remote

import com.examdple.gymus_android.auth.data.dataSource.dataStore.AuthDataStore
import com.examdple.gymus_android.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.examdple.gymus_android.auth.data.dataSource.remote.dto.request.RegisterRequestDto
import com.examdple.gymus_android.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.examdple.gymus_android.core.dto.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class UserApiServiceImpl(
    private val httpClient: HttpClient,
    private val authDataStore: AuthDataStore
) : UserApiService {
    override suspend fun login(loginRequestDto: LoginRequestDto) {
        val response = httpClient.post("http://10.0.2.2:5138/api/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequestDto)
        }
        if (response.status == HttpStatusCode.OK) {
            response.body<ApiResponse<AuthResponseDto>>().data?.let {
                authDataStore.saveAuthData(it)
            }
        }
    }

    override suspend fun register(registerRequestDto: RegisterRequestDto) {
        TODO("Not yet implemented")
    }
}