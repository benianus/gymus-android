package com.example.gymusAndroid.membership.domain.repostories

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard

interface MembershipRepository {
    suspend fun findAllMembers(
        page: Int = 1,
        pageSize: Int = 10
    ): ApiResponse<List<Member>, ErrorResponseDto>

    suspend fun registerMember(
        memberRegisterRequestDto: MemberRegisterRequestDto
    ): ApiResponse<Unit, ErrorResponseDto>

    suspend fun recordAttendance(memberId: Int): ApiResponse<Unit, ErrorResponseDto>
    suspend fun renewMembership(memberId: Int): ApiResponse<Unit, ErrorResponseDto>
    suspend fun findMemberCard(memberId: Int): ApiResponse<MemberCard?, ErrorResponseDto>
    suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): ApiResponse<Unit, ErrorResponseDto>

    suspend fun deleteMember(memberId: Int): ApiResponse<Unit, ErrorResponseDto>
}
