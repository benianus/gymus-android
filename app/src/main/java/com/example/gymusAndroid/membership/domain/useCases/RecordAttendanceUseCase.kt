package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class RecordAttendanceUseCase(private val membershipRepository: MembershipRepository) {
    suspend operator fun invoke(memberId: Int) {
        TODO()
    }
}