package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberUpdateRequestDto(
    @SerialName("phone_number")
    val phoneNumber: String,
    val email: String,
    val address: String,
    @SerialName("personal_photo")
    val personalPhoto: String
)
