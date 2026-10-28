package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.membership.domain.models.MemberCard
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class FindMemberCardUseCase(val membershipRepository: MembershipRepository) {
    suspend operator fun invoke(memberId: Int): MemberCard? {
        return membershipRepository.findMemberCard(memberId)
    }
}