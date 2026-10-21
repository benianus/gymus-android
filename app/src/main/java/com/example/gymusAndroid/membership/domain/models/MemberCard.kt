package com.example.gymusAndroid.membership.domain.models

data class MemberCard(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val joinDate: String,
    val endDate: String,
    val personalPhoto: String
)
