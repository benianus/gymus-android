package com.example.gymusAndroid.membership.domain.repostories

import androidx.paging.PagingData
import com.example.core.dto.ApiResponse
import com.example.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard
import kotlinx.coroutines.flow.Flow

interface MembershipRepository {
    suspend fun findAllMembers(
        page: Int = 1,
        pageSize: Int = 10
    ): ApiResponse<Flow<PagingData<Member>>, ErrorResponseDto>

    suspend fun registerMember(
        memberRegisterRequestDto: MemberRegisterRequestDto
    ): ApiResponse<String, ErrorResponseDto>

    suspend fun recordAttendance(memberId: Int): ApiResponse<String, ErrorResponseDto>
    suspend fun renewMembership(memberId: Int): ApiResponse<String, ErrorResponseDto>
    suspend fun findMemberCard(memberId: Int): ApiResponse<MemberCard?, ErrorResponseDto>
    suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): ApiResponse<String, ErrorResponseDto>

    suspend fun deleteMember(memberId: Int): ApiResponse<String, ErrorResponseDto>
}
