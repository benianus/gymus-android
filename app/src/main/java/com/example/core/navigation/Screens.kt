package com.example.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Tab

@Serializable
data object LoginScreen : Tab

@Serializable
data object RegisterScreen : Tab

@Serializable
data object DashboardScreen : Tab

@Serializable
data object MembershipsScreen : Tab

@Serializable
data class MemberCardScreen(val memberId: Int) : Tab

@Serializable
data object RegisterMembershipScreen : Tab

@Serializable
data class UpdateMembershipScreen(val memberId: Int) : Tab

@Serializable
data object SessionsScreen : Tab

@Serializable
data object RegisterSessionScreen : Tab

@Serializable
data object ProductsScreen : Tab

@Serializable
data class ProductDetailsScreen(val productId: Int) : Tab

@Serializable
data object SaleScreen : Tab

@Serializable
data object AddProductScreen : Tab

@Serializable
data class UpdateProductScreen(val memberId: Int) : Tab

@Serializable
data object ReportsScreen : Tab



