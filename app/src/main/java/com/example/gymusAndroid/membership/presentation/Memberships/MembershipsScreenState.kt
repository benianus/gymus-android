package com.example.gymusAndroid.membership.presentation.Memberships

import com.example.gymusAndroid.membership.domain.models.Member

data class MembershipsScreenState(
    val members: List<Member> = emptyList()
)
