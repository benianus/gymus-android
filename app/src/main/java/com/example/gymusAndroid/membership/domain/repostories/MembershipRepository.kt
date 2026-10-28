package com.example.gymusAndroid.membership.domain.repostories

import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.models.MemberCard

interface MembershipRepository {
    suspend fun findAllMembers(page: Int = 1, pageSize: Int = 10): List<Member>
    suspend fun registerMember(memberRegisterRequestDto: MemberRegisterRequestDto)
    suspend fun recordAttendance(memberId: Int)
    suspend fun renewMembership(memberId: Int)
    suspend fun findMemberCard(memberId: Int): MemberCard?
    suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    )

    suspend fun deleteMember(memberId: Int)
}
