package com.examdple.gymus_android.core.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val ACCESS_TOKEN = stringPreferencesKey("access_token")
val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
val ROLE = stringPreferencesKey("role")
val EXPIRES_AT = longPreferencesKey("expires_in")

val Context.AuthDataStore: DataStore<Preferences> by preferencesDataStore("auth_data")

fun createAuthDataStore(context: Context): DataStore<Preferences> =
    context.AuthDataStore


