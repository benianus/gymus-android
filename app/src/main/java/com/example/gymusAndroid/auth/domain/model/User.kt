package com.example.gymusAndroid.auth.domain.model

import kotlin.time.Clock
import kotlin.time.Instant

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val role: String,
    val createdAt: Instant,
    val updatedAt: Instant
) {
    constructor() : this(
        id = 0,
        username = "",
        password = "",
        role = "",
        createdAt = Clock.System.now(),
        updatedAt = Clock.System.now()
    )
}
