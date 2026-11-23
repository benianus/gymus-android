package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberCardResponseDto(
    @SerialName("member_card_id")
    val memberCardId: Int,
    @SerialName("member_id")
    val memberId: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val birthdate: LocalDate,
    @SerialName("join_date")
    val joinDate: LocalDate,
    @SerialName("start_date")
    val startDate: LocalDate,
    @SerialName("end_date")
    val endDate: LocalDate,
    @SerialName("personal_photo")
    val personalPhoto: String,
    @SerialName("check_date")
    val checkDate: String?,
    @SerialName("last_renewal")
    val lastRenewal: String?,
    @SerialName(value = "active")
    val isActive: Boolean,
    @SerialName(value = "checked")
    val isChecked: Boolean
)
