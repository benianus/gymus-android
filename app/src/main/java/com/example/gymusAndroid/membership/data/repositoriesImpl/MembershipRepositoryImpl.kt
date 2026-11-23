package com.example.gymusAndroid.membership.data.repositoriesImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.dto.ApiResponse
import com.example.core.dto.Error
import com.example.core.dto.ErrorResponseDto
import com.example.core.dto.Success
import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository
import com.example.gymusAndroid.membership.toModel
import kotlinx.coroutines.flow.Flow

class MembershipRepositoryImpl(
    private val membershipApiService: MembershipApiService,
) : MembershipRepository {
    override suspend fun findAllMembers(
        page: Int,
        pageSize: Int
    ): ApiResponse<Flow<PagingData<Member>>, ErrorResponseDto> {
        return Success(
            Pager(
                config = PagingConfig(
                    pageSize = pageSize,
                    enablePlaceholders = true,
                    initialLoadSize = 10
                ),
                pagingSourceFactory = {
                    MembershipPagingSource(membershipApiService)
                }
            ).flow
        )
    }

    override suspend fun registerMember(memberRegisterRequestDto: MemberRegisterRequestDto): ApiResponse<Unit, ErrorResponseDto> {
        TODO("Not yet implemented")
    }

    override suspend fun recordAttendance(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        return membershipApiService.recordAttendance(memberId)
    }

    override suspend fun renewMembership(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        return membershipApiService.renewMembership(memberId)
    }

    override suspend fun findMemberCard(
        memberId: Int
    ): ApiResponse<MemberCard?, ErrorResponseDto> {
        return when (
            val result = membershipApiService.findMemberCard(
                memberId = memberId
            )
        ) {
            is Error -> Error(result.error)
            is Success -> Success(result.data.toModel())
        }
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