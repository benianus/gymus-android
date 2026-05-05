package com.examdple.gymus_android.auth.data.dataSource.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.examdple.gymus_android.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.examdple.gymus_android.core.config.ACCESS_TOKEN
import com.examdple.gymus_android.core.config.EXPIRES_AT
import com.examdple.gymus_android.core.config.REFRESH_TOKEN
import com.examdple.gymus_android.core.config.ROLE
import kotlinx.coroutines.flow.map

class AuthDataStore(
    private val authDataStore: DataStore<Preferences>,
) {
    val accessToken = authDataStore.data.map { it[ACCESS_TOKEN] ?: "" }
    val refreshToken = authDataStore.data.map { it[REFRESH_TOKEN] ?: "" }
    val role = authDataStore.data.map { it[ROLE] ?: "" }
    val expiresAt = authDataStore.data.map { it[EXPIRES_AT] ?: 0L }

    suspend fun saveAuthData(authResponseDto: AuthResponseDto) {
        authDataStore.updateData { preferences ->
            preferences.toMutablePreferences().also {
                it[ACCESS_TOKEN] = authResponseDto.accessToken
                it[REFRESH_TOKEN] = authResponseDto.refreshToken
                it[ROLE] = authResponseDto.role
                it[EXPIRES_AT] = authResponseDto.expiresAt
            }
        }
    }

    suspend fun clearAuthData() {
        authDataStore.edit {
            it.clear()
        }
    }
}