package com.example.gymusAndroid.core.config

import com.example.gymusAndroid.auth.data.dataSource.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RefreshTokenRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.RefreshTokenResponseDto
import com.example.gymusAndroid.core.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

fun createHttpClient(authDataStore: AuthDataStore): HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        })
    }

    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }

    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens(
                    authDataStore.accessToken.first(),
                    authDataStore.refreshToken.first()
                )
            }

            refreshTokens {
                val refreshTokenResponse = client.post("${BASE_URL}/auth/refresh") {
                    markAsRefreshTokenRequest()
                    contentType(ContentType.Application.Json)
                    setBody(
                        RefreshTokenRequestDto(
                            username = authDataStore.username.first(),
                            refreshToken = oldTokens?.refreshToken ?: ""
                        )
                    )
                }

                val newTokens = refreshTokenResponse.body<RefreshTokenResponseDto>()

                BearerTokens(
                    newTokens.accessToken,
                    newTokens.refreshToken
                ).also {
                    authDataStore.saveAuthData(
                        authResponseDto = AuthResponseDto(
                            accessToken = it.accessToken,
                            refreshToken = it.refreshToken ?: "",
                            username = "",
                            role = "",
                            expiresIn = 3L
                        )
                    )
                }
            }
        }
    }
}
