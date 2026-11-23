package com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MemberRegisterRequestDto(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val email: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    val address: String,
    @SerialName("medical_certificate")
    val medicalCertificate: String,
    @SerialName("birth_certificate")
    val birthCertificate: String,
    @SerialName("personal_photo")
    val personalPhoto: String,
    @SerialName("parental_authorization")
    val parentalAuthorization: String?,
    @SerialName("membership_type")
    val membershipType: String
)
