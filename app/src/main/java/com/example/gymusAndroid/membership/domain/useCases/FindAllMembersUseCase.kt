package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class FindAllMembersUseCase(private val membershipsRepository: MembershipRepository) {
    suspend operator fun invoke(
        page: Int = 1,
        pageSize: Int = 10
    ): ApiResponse<List<Member>, ErrorResponseDto> {
        return when (val result =
                membershipsRepository.findAllMembers(page, pageSize)) {
            is ApiResponse.Error -> ApiResponse.Error(ErrorResponseDto(result.error.message))
            is ApiResponse.Success -> ApiResponse.Success(result.data)
        }
    }
}