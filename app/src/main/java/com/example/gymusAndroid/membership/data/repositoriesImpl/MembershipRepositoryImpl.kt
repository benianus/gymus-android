package com.example.gymusAndroid.membership.data.repositoriesImpl

import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class MembershipRepositoryImpl(
    private val membershipApiService: MembershipApiService
) : MembershipRepository {
    override suspend fun findAllMembers(
        page: Int,
        pageSize: Int
    ): List<Member> =
            TODO("Not yet implemented")

    override suspend fun registerMember(memberRegisterRequestDto: MemberRegisterRequestDto) {
        TODO("Not yet implemented")
    }

    override suspend fun recordAttendance(memberId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun renewMembership(memberId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun findMemberCard(memberId: Int): MemberCard? {
        TODO("Not yet implemented")
    }

    override suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMember(memberId: Int) {
        TODO("Not yet implemented")
    }

}