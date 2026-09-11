package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class MemberCardResponseDto(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate,
    val joinDate: LocalDate,
    val endDate: LocalDate,
    val personalPhoto: String
)
