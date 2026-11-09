package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.domain.models.MemberCard
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class FindMemberCardUseCase(private val membershipRepository: MembershipRepository) {
    suspend operator fun invoke(memberId: Int): ApiResponse<MemberCard?, ErrorResponseDto> {
        return membershipRepository.findMemberCard(memberId)
    }
}