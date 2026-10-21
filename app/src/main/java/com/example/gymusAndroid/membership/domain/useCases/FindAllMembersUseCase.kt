package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class FindAllMembersUseCase(val fakeMembershipsRepository: MembershipRepository) {
    suspend operator fun invoke(page: Int = 1, pageSize: Int = 10): List<Member> {
        return fakeMembershipsRepository.findAllMembers(page, pageSize)
            .sortedByDescending { it.createdAt }
    }
}