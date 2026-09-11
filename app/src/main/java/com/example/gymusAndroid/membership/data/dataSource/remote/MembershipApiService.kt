package com.example.gymusAndroid.membership.data.dataSource.remote

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.PagedResponse
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberCardResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberResponseDto

interface MembershipApiService {
    suspend fun findAllMembers(
        page: Int = 1,
        pageSize: Int = 9
    ): PagedResponse<ApiResponse<List<MemberResponseDto>>>

    suspend fun registerMember(memberRegisterRequestDto: MemberRegisterRequestDto)
    suspend fun recordAttendance(memberId: Int)
    suspend fun renewMembership(memberId: Int)
    suspend fun findMemberCard(memberId: Int): ApiResponse<MemberCardResponseDto>
    suspend fun updateMember(memberId: Int, memberUpdateRequestDto: MemberUpdateRequestDto)
    suspend fun deleteMember(memberId: Int)
}