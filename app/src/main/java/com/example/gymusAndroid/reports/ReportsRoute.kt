package com.example.gymusAndroid.reports

import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ReportsRoute(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel()
) {

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            ReportsCompactScreen(
                navController = navController,
                modifier = Modifier,
                onClickLogout = loginViewModel::logout,
            )
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
fun ReportsExpandedScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun ReportsMediumScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun ReportsCompactScreen(
    navController: NavHostController,
    onClickLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                navHostController = navController,
                title = "Reports",
                isActualScreen = true
            )
        },
        bottomBar = {
            BottomNavBar(navHostController = navController)
        }
    ) { innerPadding ->

    }
}
