package com.example.gymusAndroid.auth

import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.RegisterRequestDto
import com.example.gymusAndroid.auth.domain.model.User

fun User.toLoginDto(): LoginRequestDto =
    LoginRequestDto(
        username = username,
        password = password
    )

fun User.toRegisterDto(): RegisterRequestDto =
    RegisterRequestDto(
        username = username,
        password = password
    )