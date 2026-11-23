package com.example.gymusAndroid.membership.domain.useCases

import androidx.paging.PagingData
import com.example.core.dto.ApiResponse
import com.example.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository
import kotlinx.coroutines.flow.Flow

class FindAllMembersUseCase(
    private val membershipsRepository: MembershipRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        pageSize: Int = 10
    ): ApiResponse<Flow<PagingData<Member>>, ErrorResponseDto> {
        return membershipsRepository.findAllMembers()
    }
}