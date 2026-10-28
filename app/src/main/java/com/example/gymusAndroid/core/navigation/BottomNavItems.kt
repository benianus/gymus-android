package com.example.gymusAndroid.core.navigation

import com.example.gymusAndroid.R


data class BottomNavItem(
    val icon: Int,
    val contentDescription: String,
    val label: String,
    val route: Tab
)

val bottomNavItems = listOf(
    BottomNavItem(
        icon = R.drawable.baseline_dashboard_24,
        contentDescription = "dashboard",
        label = "Dashboard",
        route = DashboardScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_person_24,
        contentDescription = "memberships",
        label = "Members",
        route = MembershipsScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_access_time_filled_24,
        contentDescription = "sessions",
        label = "Sessions",
        route = SessionsScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_store_24,
        contentDescription = "products",
        label = "Store",
        route = ProductsScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_assessment_24,
        contentDescription = "reports",
        label = "Reports",
        route = ReportsScreen,
    ),
)