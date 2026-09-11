package com.example.gymusAndroid.membership.data.repositoriesImpl

import com.example.gymusAndroid.membership.data.dataSource.remote.MembershipApiService
import com.example.gymusAndroid.membership.domain.models.Member
import com.example.gymusAndroid.membership.domain.repostories.MembershipRepository

class MembershipRepositoryImpl(
    private val membershipApiService: MembershipApiService
) : MembershipRepository {
    override suspend fun findAllMembers(page: Int, pageSize: Int): List<Member> =
            TODO("Not yet implemented")

    override suspend fun registerMember(): Unit = TODO("Not yet implemented")
    override suspend fun recordAttendance(): Unit = TODO("Not yet implemented")
    override suspend fun renewMembership(): Unit = TODO("Not yet implemented")
    override suspend fun findMemberCard(): Unit = TODO("Not yet implemented")
    override suspend fun updateMember(): Unit = TODO("Not yet implemented")
    override suspend fun deleteMember(): Unit = TODO("Not yet implemented")
}