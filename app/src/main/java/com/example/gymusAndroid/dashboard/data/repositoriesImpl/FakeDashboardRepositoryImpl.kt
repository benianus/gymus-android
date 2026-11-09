package com.example.gymusAndroid.dashboard.data.repositoriesImpl

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
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
            id = 1,
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
            id = 2,
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

    override suspend fun getActiveMembers(): ApiResponse<Int, ErrorResponseDto> =
            ApiResponse.Success(100)

    override suspend fun getTodaysRevenue(): ApiResponse<Double, ErrorResponseDto> =
            ApiResponse.Success(1000.0)

    override suspend fun getRecentCheckIns(): ApiResponse<Set<Member>, ErrorResponseDto> =
            ApiResponse.Success(members)
}