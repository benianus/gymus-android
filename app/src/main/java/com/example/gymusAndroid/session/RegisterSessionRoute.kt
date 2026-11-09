package com.example.gymusAndroid.session

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
fun RegisterSessionRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            RegisterSessionCompactScreen(
                onClickLogout = loginViewModel::logout,
                navController = navController,
                modifier = Modifier,
            )
        }

        WindowWidthSizeClass.Medium -> {
            RegisterSessionMediumScreen(modifier = Modifier)
        }

        WindowWidthSizeClass.Expanded -> {
            RegisterSessionExpandedScreen(modifier = Modifier)
        }
    }
}

@Composable
fun RegisterSessionExpandedScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun RegisterSessionMediumScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun RegisterSessionCompactScreen(
    navController: NavHostController,
    modifier: Modifier,
    onClickLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                navHostController = navController,
                title = "Register Session",
                isActualScreen = false
            )
        },
        bottomBar = { BottomNavBar(navHostController = navController) }
    ) { innerPadding ->

    }
}