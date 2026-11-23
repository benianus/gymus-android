package com.example.core.config

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.dto.Error
import com.example.core.dto.ErrorResponseDto
import com.example.core.dto.Success
import com.example.core.helpers.BASE_URL
import com.example.core.helpers.apiResponse
import com.example.gymusAndroid.auth.data.dataSource.local.dataStore.AuthDataStore
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RefreshTokenRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.RefreshTokenResponseDto
import io.ktor.client.HttpClient
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

fun createHttpClient(authDataStore: AuthDataStore, dataStore: DataStore<Preferences>) =
        HttpClient(CIO) {
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
                            accessToken = authDataStore.accessToken.first(),
                            refreshToken = authDataStore.refreshToken.first()
                        )
                    }
                    sendWithoutRequest { true }
                    refreshTokens {
                        try {
                            val response = client.post("${BASE_URL}/auth/refresh") {
                                markAsRefreshTokenRequest()
                                contentType(ContentType.Application.Json)
                                setBody(
                                    RefreshTokenRequestDto(
                                        username = authDataStore.username.first(),
                                        refreshToken = authDataStore.refreshToken
                                            .first()
                                            .ifBlank { oldTokens?.refreshToken ?: "" }
                                    )
                                )
                            }

                            val result = apiResponse<RefreshTokenResponseDto, ErrorResponseDto>(
                                response
                            )

                            when (result) {
                                is Error<*, ErrorResponseDto> -> null
                                is Success<RefreshTokenResponseDto, *> -> {
                                    BearerTokens(
                                        accessToken = result.data.accessToken,
                                        refreshToken = result.data.refreshToken
                                    ).also { bearerTokens ->
                                        dataStore.updateData { preferences ->
                                            preferences.toMutablePreferences()
                                                .also { mutablePreferences ->
                                                    mutablePreferences[ACCESS_TOKEN] =
                                                            result.data.accessToken
                                                    mutablePreferences[REFRESH_TOKEN] =
                                                            result.data.refreshToken
                                                }
                                        }
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            null
                        }

                    }
                }
            }

        }


