package com.example.gymusAndroid.dashboard.data.repositoriesImpl

import com.example.gymusAndroid.dashboard.domain.models.Member
import com.example.gymusAndroid.dashboard.domain.repositories.DashboardRepository
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

class FakeDashboardRepositoryImpl : DashboardRepository {
    val members = mutableSetOf(
        Member(
            fullName = "Mohamed BENIANE",
            status = "Active",
            checkedInAt = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .time
                .format(LocalTime.Format {
                    hour()
                    chars(":")
                    minute()
                }),
            personalPhoto = ""
        ),
        Member(
            fullName = "Abdellah BENIANE",
            status = "Expired",
            checkedInAt = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .time
                .format(LocalTime.Format {
                    hour()
                    chars(":")
                    minute()
                }),
            personalPhoto = ""
        )
    )

    override suspend fun getActiveMembers() = 100

    override suspend fun getTodaysRevenue() = 1000.0

    override suspend fun getRecentCheckIns(): Set<Member> = members
}