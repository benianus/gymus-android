package com.example.gymusAndroid.session.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.core.components.BottomNavBar
import com.example.core.components.TopBar
import com.example.gymusAndroid.R
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
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
                title = stringResource(R.string.register_session),
                showNavigateUpIcon = true
            )
        },
        bottomBar = { BottomNavBar(navHostController = navController) }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) { }
    }
}