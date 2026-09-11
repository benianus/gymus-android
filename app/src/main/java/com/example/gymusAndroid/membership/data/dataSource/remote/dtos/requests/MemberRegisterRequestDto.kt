package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests

import kotlinx.serialization.Serializable

@Serializable
data class MemberRegisterRequestDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val medicalCertificate: String,
    val birthCertificate: String,
    val personalPhoto: String,
    val parentalAuthorization: String?,
    val membershipType: String
)
