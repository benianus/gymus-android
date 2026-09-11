package com.example.gymusAndroid.membership.domain.models

data class Member(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val birthdate: String,
    val personalPhoto: String,
    val endDate: String,
    val createdAt: String,
    val updatedAt: String,
    val isActive: Boolean
)

