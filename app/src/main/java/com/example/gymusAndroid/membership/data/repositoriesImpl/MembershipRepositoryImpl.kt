package com.example.gymusAndroid.membership.data.repositoriesImpl

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class MembershipRepositoryImpl(
    private val membershipApiService: MembershipApiService
) : MembershipRepository {
    override suspend fun findAllMembers(
        page: Int,
        pageSize: Int
    ): ApiResponse<List<Member>, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun registerMember(memberRegisterRequestDto: MemberRegisterRequestDto): ApiResponse<Unit, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun recordAttendance(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun renewMembership(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun findMemberCard(memberId: Int): ApiResponse<MemberCard?, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): ApiResponse<Unit, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMember(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        TODO("Not yet implemented")
    }
}