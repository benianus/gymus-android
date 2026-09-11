package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class MemberResponseDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val birthDate: LocalDate,
    val personalPhoto: String,
    val isActive: Boolean,
    val createdAt: LocalDate,
    val updatedAt: LocalDate
)
