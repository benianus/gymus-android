package com.example.gymusAndroid.auth.data.dataSource.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import com.example.gymusAndroid.core.config.ACCESS_TOKEN
import com.example.gymusAndroid.core.config.EXPIRES_IN
import com.example.gymusAndroid.core.config.REFRESH_TOKEN
import com.example.gymusAndroid.core.config.ROLE
import com.example.gymusAndroid.core.config.USERNAME
import kotlinx.coroutines.flow.map

class AuthDataStore(private val dataStore: DataStore<Preferences>) {
    val accessToken = dataStore.data.map { it[ACCESS_TOKEN] ?: "" }
    val refreshToken = dataStore.data.map { it[REFRESH_TOKEN] ?: "" }
    val role = dataStore.data.map { it[ROLE] ?: "" }
    val expiresIn = dataStore.data.map { it[EXPIRES_IN] ?: 0L }
    val username = dataStore.data.map { it[USERNAME] ?: "benianus" }
    suspend fun saveAuthData(authResponseDto: AuthResponseDto) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().also {
                it[ACCESS_TOKEN] = authResponseDto.accessToken
                it[REFRESH_TOKEN] = authResponseDto.refreshToken
                it[ROLE] = authResponseDto.role
                it[EXPIRES_IN] = authResponseDto.expiresIn
                it[USERNAME] = authResponseDto.username
            }
        }
    }

    suspend fun clearAuthData() {
        dataStore.edit {
            it.clear()
        }
    }
}