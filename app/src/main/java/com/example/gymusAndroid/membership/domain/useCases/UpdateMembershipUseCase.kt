package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class UpdateMembershipUseCase(private val membershipRepository: MembershipRepository) {
    suspend operator fun invoke(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        return membershipRepository.updateMember(
            memberId,
            MemberUpdateRequestDto(
                phoneNumber = TODO(),
                email = TODO(),
                address = TODO(),
                personalPhoto = TODO()
            )
        )
    }
}