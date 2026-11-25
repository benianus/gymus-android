package com.example.gymusAndroid.membership.domain.useCases

import com.example.core.dto.ApiResponse
import com.example.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class RenewMembershipUseCase(private val membershipRepository: MembershipRepository) {
    suspend operator fun invoke(memberId: Int): ApiResponse<String, ErrorResponseDto> {
        return membershipRepository.renewMembership(memberId)
    }
}