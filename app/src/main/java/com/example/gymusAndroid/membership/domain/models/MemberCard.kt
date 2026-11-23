package com.example.gymusAndroid.membership.domain.models

data class MemberCard(
    val memberCardId: Int,
    val memberId: Int,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val joinDate: String,
    val endDate: String,
    val checkDate: String?,
    val lastRenewal: String?,
    val personalPhoto: String,
    val isActive: Boolean,
    val isChecked: Boolean
)
