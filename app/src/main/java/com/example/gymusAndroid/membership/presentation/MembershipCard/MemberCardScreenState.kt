package com.example.gymusAndroid.membership.presentation.MembershipCard

import com.example.gymusAndroid.membership.domain.models.MemberCard

data class MemberCardScreenState(
    val memberCard: MemberCard? = null,
    val loading: Boolean = false,
    val error: String? = null
)
