package com.example.gymusAndroid.membership

import com.example.core.dto.PagedResponse
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberCardResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberResponseDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard


fun MemberResponseDto.toModel() =
        Member(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            phoneNumber = phoneNumber,
            address = address,
            birthdate = birthdate.toString(),
            personalPhoto = personalPhoto,
            endDate = endDate.toString(),
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString(),
            isActive = isActive
        )

fun PagedResponse<List<MemberResponseDto>>.toModel() =
        PagedResponse(
            content = content.map { it.toModel() },
            pageSize = pageSize,
            totalItems = totalItems,
            totalPages = totalPages,
            hasNext = hasNext,
            hasPrevious = hasPrevious
        )

fun MemberCardResponseDto.toModel() =
        MemberCard(
            memberCardId = memberCardId,
            memberId = memberId,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthdate.toString(),
            joinDate = joinDate.toString(),
            endDate = endDate.toString(),
            personalPhoto = personalPhoto,
            isActive = isActive,
            checkDate = checkDate,
            isChecked = isChecked,
            lastRenewal = lastRenewal
        )