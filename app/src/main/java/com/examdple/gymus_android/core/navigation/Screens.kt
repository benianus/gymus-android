package com.examdple.gymus_android.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Register

@Serializable
object Dashboard

@Serializable
object Memberships

@Serializable
object RegisterMembership

@Serializable
object Sessions

@Serializable
object RegisterSession

@Serializable
object Products

@Serializable
object Reports

@Serializable
data class MemberCard(val memberId: Int)

