package com.example.gymusAndroid.auth.data.dataSource.local.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.core.config.ACCESS_TOKEN
import com.example.core.config.EXPIRES_IN
import com.example.core.config.REFRESH_TOKEN
import com.example.core.config.ROLE
import com.example.core.config.USERNAME
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.response.AuthResponseDto
import kotlinx.coroutines.flow.map

class AuthDataStore(private val dataStore: DataStore<Preferences>) {
    val accessToken = dataStore.data.map {
        it[ACCESS_TOKEN] ?: ""
    }
    val refreshToken = dataStore.data.map {
        it[REFRESH_TOKEN] ?: ""
    }
    val role = dataStore.data.map {
        it[ROLE] ?: "OWNER"
    }
    val expiresIn = dataStore.data.map {
        it[EXPIRES_IN] ?: 0L
    }
    val username = dataStore.data.map {
        it[USERNAME] ?: "benianus"
    }

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