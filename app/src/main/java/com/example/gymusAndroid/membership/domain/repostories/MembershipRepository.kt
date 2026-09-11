package com.example.gymusAndroid.membership.domain.repostories

import com.example.gymusAndroid.membership.domain.models.Member

interface MembershipRepository {
    suspend fun findAllMembers(page: Int = 1, pageSize: Int = 10): List<Member>
    suspend fun registerMember()
    suspend fun recordAttendance()
    suspend fun renewMembership()
    suspend fun findMemberCard()
    suspend fun updateMember()
    suspend fun deleteMember()
}
