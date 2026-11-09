package com.example.gymusAndroid.membership.data.dataSource.remote

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.core.dto.PagedResponse
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberCardResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberResponseDto
import io.ktor.client.HttpClient

class MembershipApiServiceImpl(val httpClient: HttpClient) :
    MembershipApiService {
    override suspend fun findAllMembers(
        page: Int,
        pageSize: Int
    ): PagedResponse<ApiResponse<List<MemberResponseDto>, ErrorResponseDto>> {
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

    override suspend fun findMemberCard(memberId: Int): ApiResponse<MemberCardResponseDto, ErrorResponseDto> {
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