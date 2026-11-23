package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberResponseDto(
    val id: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("email")
    val email: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    val address: String,
    val birthdate: LocalDate,
    @SerialName("personal_photo")
    val personalPhoto: String,
    @SerialName("end_date")
    val endDate: LocalDate,
    @SerialName(value = "active")
    val isActive: Boolean,
    @SerialName("created_at")
    val createdAt: LocalDateTime,
    @SerialName("updated_at")
    val updatedAt: LocalDateTime
)
