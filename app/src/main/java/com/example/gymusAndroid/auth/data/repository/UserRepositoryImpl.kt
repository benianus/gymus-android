package com.example.gymusAndroid.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.gymusAndroid.auth.data.dataSource.local.UserDao
import com.example.gymusAndroid.auth.data.dataSource.remote.UserApiService
import com.example.gymusAndroid.auth.domain.model.User
import com.example.gymusAndroid.auth.domain.repository.UserRepository
import com.example.gymusAndroid.auth.toLoginDto
import com.example.gymusAndroid.auth.toRegisterDto

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val userDao: UserDao,
    private val dataStore: DataStore<Preferences>
) : UserRepository {
    override suspend fun login(user: User): String? = userApiService.login(user.toLoginDto())

    override suspend fun register(user: User): String? =
        userApiService.register(user.toRegisterDto())

    override suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
    }
}