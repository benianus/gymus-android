package com.jetbrains.gymusserverjava.memberships.dtos.responses

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentMemberChecksInDto(
    val id: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("personal_photo")
    val personalPhoto: String,
    @SerialName("membership_type")
    val membershipType: String,
    val status: Boolean,
    @SerialName("checked_in_at")
    val checkedInAt: LocalDateTime
)
