package com.example.gymusAndroid.dashboard

import com.example.gymusAndroid.dashboard.domain.models.Member
import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

fun RecentMemberChecksInDto.toModel(): Member {
    return Member(
        fullName = "$firstName $lastName",
        status = if (status) "Active" else "Expired",
        checkedInAt = checkedInAt.time.format(LocalTime.Format {
            hour()
            char(':')
            minute()
        }),
        personalPhoto = personalPhoto
    )
}