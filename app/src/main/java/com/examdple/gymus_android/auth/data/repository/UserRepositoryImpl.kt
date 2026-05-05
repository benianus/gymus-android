package com.examdple.gymus_android.auth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.examdple.gymus_android.auth.data.dataSource.local.UserDao
import com.examdple.gymus_android.auth.data.dataSource.remote.UserApiService
import com.examdple.gymus_android.auth.domain.model.User
import com.examdple.gymus_android.auth.domain.repository.UserRepository
import com.examdple.gymus_android.auth.toLoginDto

class UserRepositoryImpl(
    private val userApiService: UserApiService,
    private val userDao: UserDao,
    private val dataStore: DataStore<Preferences>
) : UserRepository {
    override suspend fun login(user: User) {
        userApiService.login(user.toLoginDto())
    }

    override suspend fun register() {
        TODO("Not yet implemented")
    }

    override suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
    }
}