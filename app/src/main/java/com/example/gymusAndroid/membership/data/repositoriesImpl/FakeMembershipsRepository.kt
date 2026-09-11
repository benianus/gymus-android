package com.example.gymusAndroid.membership.data.repositoriesImpl

import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository
import kotlin.time.Clock

class FakeMembershipsRepository : MembershipRepository {
    val members = mutableListOf(
        Member(
            id = 1,
            firstName = "Mohamed",
            lastName = "BENIANE",
            email = "beniane39@gmail.com",
            phoneNumber = "+213658854636",
            address = "city 36 apartment bloc b n08",
            birthdate = "1994-02-11",
            personalPhoto = "photo",
            endDate = "2026-10-12",
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
            isActive = true
        )
    )

    override suspend fun findAllMembers(page: Int, pageSize: Int): List<Member> {
        return this.members
    }

    override suspend fun registerMember() {
        TODO("Not yet implemented")
    }

    override suspend fun recordAttendance() {
        TODO("Not yet implemented")
    }

    override suspend fun renewMembership() {
        TODO("Not yet implemented")
    }

    override suspend fun findMemberCard() {
        TODO("Not yet implemented")
    }

    override suspend fun updateMember() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMember() {
        TODO("Not yet implemented")
    }
}