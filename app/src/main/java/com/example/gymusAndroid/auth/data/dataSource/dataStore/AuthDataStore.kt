package com.example.gymusAndroid.auth.data.dataSource.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.core.config.ACCESS_TOKEN
import com.example.gymusAndroid.core.config.EXPIRES_IN
import com.example.gymusAndroid.core.config.REFRESH_TOKEN
import com.example.gymusAndroid.core.config.ROLE
import kotlinx.coroutines.flow.map

class AuthDataStore(private val authDataStore: DataStore<Preferences>) {
    val accessToken = authDataStore.data.map { it[ACCESS_TOKEN] ?: "" }
    val refreshToken = authDataStore.data.map { it[REFRESH_TOKEN] ?: "" }
    val role = authDataStore.data.map { it[ROLE] ?: "" }
    val expiresIn = authDataStore.data.map { it[EXPIRES_IN] ?: 0L }

    suspend fun saveAuthData(authResponseDto: AuthResponseDto) {
        authDataStore.updateData { preferences ->
            preferences.toMutablePreferences().also {
                it[ACCESS_TOKEN] = authResponseDto.accessToken
                it[REFRESH_TOKEN] = authResponseDto.refreshToken
                it[ROLE] = authResponseDto.role
                it[EXPIRES_IN] = authResponseDto.expiresIn
            }
        }
    }

    suspend fun clearAuthData() {
        authDataStore.edit {
            it.clear()
        }
    }
}