package com.examdple.gymus_android.auth.domain.model

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
    constructor() : this(0, "", "", "", Clock.System.now(), Clock.System.now())
}
