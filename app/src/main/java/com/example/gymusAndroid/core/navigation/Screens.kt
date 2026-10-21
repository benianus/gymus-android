package com.example.gymusAndroid.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Tab

@Serializable
data object Login : Tab

@Serializable
data object Register : Tab

@Serializable
data object Dashboard : Tab

@Serializable
data object Memberships : Tab

@Serializable
data object RegisterMembership : Tab

@Serializable
data object Sessions : Tab

@Serializable
data object RegisterSession : Tab

@Serializable
data object Products : Tab

@Serializable
data object Reports : Tab

@Serializable
data class MemberCard(val memberId: Int) : Tab

