package com.example.gymusAndroid.membership.data.dataSource.remote

import com.example.core.dto.ApiResponse
import com.example.core.dto.ErrorResponseDto
import com.example.core.dto.PagedResponse
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberCardResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberResponseDto

interface MembershipApiService {
    suspend fun findAllMembers(
        page: Int = 1,
        pageSize: Int = 9
    ): ApiResponse<PagedResponse<List<MemberResponseDto>>, ErrorResponseDto>

    suspend fun registerMember(
        memberRegisterRequestDto: MemberRegisterRequestDto
    ): ApiResponse<Unit, ErrorResponseDto>

    suspend fun recordAttendance(memberId: Int): ApiResponse<Unit, ErrorResponseDto>
    suspend fun renewMembership(memberId: Int): ApiResponse<Unit, ErrorResponseDto>
    suspend fun findMemberCard(memberId: Int): ApiResponse<MemberCardResponseDto, ErrorResponseDto>
    suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): ApiResponse<Unit, ErrorResponseDto>

    suspend fun deleteMember(memberId: Int): ApiResponse<Unit, ErrorResponseDto>
}