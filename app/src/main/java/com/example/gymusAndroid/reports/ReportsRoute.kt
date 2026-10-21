package com.example.gymusAndroid.reports

import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar

@Composable
fun ReportsRoute(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            ReportsCompactScreen(navController = navController, modifier = Modifier)
        }

        WindowWidthSizeClass.Medium -> {
            ReportsMediumScreen(modifier = Modifier)
        }

        WindowWidthSizeClass.Expanded -> {
            ReportsExpandedScreen(modifier = Modifier)
        }
    }
}

@Composable
fun ReportsExpandedScreen(modifier: Modifier.Companion) {
    TODO("Not yet implemented")
}

@Composable
fun ReportsMediumScreen(modifier: Modifier.Companion) {
    TODO("Not yet implemented")
}

@Composable
fun ReportsCompactScreen(navController: NavHostController, modifier: Modifier.Companion) {
    Scaffold(
        topBar = {
            TopBar(
                navHostController = navController,
                title = "Reports",
                isActualScreen = true
            )
        },
        bottomBar = {
            BottomNavBar(navHostController = navController)
        }
    ) { }
}
