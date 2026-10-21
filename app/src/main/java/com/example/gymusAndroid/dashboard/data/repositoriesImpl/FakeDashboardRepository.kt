package com.example.gymusAndroid.dashboard.data.repositoriesImpl

import com.example.gymusAndroid.dashboard.domain.models.Member
import kotlin.time.Clock

class FakeDashboardRepository {
    val members = listOf(
        Member(
            fullName = "Mohamed BENIANE",
            status = true,
            checkedInAt = Clock.System.now().toString(),
            profilePhoto = ""
        )
    )
}