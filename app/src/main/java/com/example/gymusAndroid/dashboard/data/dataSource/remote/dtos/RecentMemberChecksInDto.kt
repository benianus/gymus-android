package com.jetbrains.gymusserverjava.memberships.dtos.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class RecentMemberChecksInDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val personalPhoto: String,
    val membershipType: String,
    val status: Boolean,
    val checkedInAt: LocalDateTime
)
