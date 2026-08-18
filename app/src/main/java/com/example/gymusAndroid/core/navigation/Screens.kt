package com.example.gymusAndroid.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Login

@Serializable
data object Register

@Serializable
data object Dashboard

@Serializable
data object Memberships

@Serializable
data object RegisterMembership

@Serializable
data object Sessions

@Serializable
data object RegisterSession

@Serializable
data object Products

@Serializable
data object Reports

@Serializable
data class MemberCard(val memberId: Int)

