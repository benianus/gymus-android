package com.example.gymusAndroid.membership.domain.useCases

import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class FindAllMembersUseCase(val membershipsRepository: MembershipRepository) {
    suspend operator fun invoke(
        page: Int = 1,
        pageSize: Int = 10
    ): List<Member> {
        return membershipsRepository.findAllMembers(page, pageSize)
            .sortedByDescending { it.createdAt }
    }
}