package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class RegisterMemberUseCase(private val membershipRepository: MembershipRepository) {
    suspend operator fun invoke(): ApiResponse<Unit, ErrorResponseDto> {
        return membershipRepository.registerMember(
            MemberRegisterRequestDto(
                firstName = TODO(),
                lastName = TODO(),
                email = TODO(),
                phoneNumber = TODO(),
                address = TODO(),
                medicalCertificate = TODO(),
                birthCertificate = TODO(),
                personalPhoto = TODO(),
                parentalAuthorization = TODO(),
                membershipType = TODO()
            )
        )
    }
}