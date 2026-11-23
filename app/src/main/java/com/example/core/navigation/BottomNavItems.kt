package com.example.core.navigation

import com.example.gymusAndroid.R


data class BottomNavItem(
    val icon: Int,
    val contentDescription: String,
    val label: String,
    val route: Tab
)

enum class BottomNavItemsNames {
    Dashboard,
    Members,
    Sessions,
    Store,
    Reports
}

val bottomNavItems = listOf(
    BottomNavItem(
        icon = R.drawable.baseline_dashboard_24,
        contentDescription = BottomNavItemsNames.Dashboard.name,
        label = BottomNavItemsNames.Dashboard.name,
        route = DashboardScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_person_24,
        contentDescription = BottomNavItemsNames.Members.name,
        label = BottomNavItemsNames.Members.name,
        route = MembershipsScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_access_time_filled_24,
        contentDescription = BottomNavItemsNames.Sessions.name,
        label = BottomNavItemsNames.Sessions.name,
        route = SessionsScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_store_24,
        contentDescription = BottomNavItemsNames.Store.name,
        label = BottomNavItemsNames.Store.name,
        route = ProductsScreen,
    ),
    BottomNavItem(
        icon = R.drawable.baseline_assessment_24,
        contentDescription = BottomNavItemsNames.Reports.name,
        label = BottomNavItemsNames.Reports.name,
        route = ReportsScreen,
    ),
)