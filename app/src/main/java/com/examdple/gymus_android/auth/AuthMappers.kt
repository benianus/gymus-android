package com.examdple.gymus_android.auth

import com.examdple.gymus_android.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.examdple.gymus_android.auth.domain.model.User

fun User.toLoginDto(): LoginRequestDto = LoginRequestDto(
    username = username,
    password = password
)