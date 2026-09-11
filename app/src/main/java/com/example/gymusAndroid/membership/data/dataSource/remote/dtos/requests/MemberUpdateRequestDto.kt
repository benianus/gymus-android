package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests

import kotlinx.serialization.Serializable

@Serializable
data class MemberUpdateRequestDto(
    val phoneNumber: String,
    val email: String,
    val address: String,
    val personalPhoto: String
)
