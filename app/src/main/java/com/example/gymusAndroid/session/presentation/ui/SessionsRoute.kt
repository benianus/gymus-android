package com.example.gymusAndroid.session.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.core.components.BottomNavBar
import com.example.core.components.TopBar
import com.example.core.navigation.RegisterSessionScreen
import com.example.gymusAndroid.R
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SessionsRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            SessionsCompactScreen(
                onClickLogout = loginViewModel::logout,
                navController = navController,
                modifier = Modifier
            )
        }

        WindowWidthSizeClass.Medium -> {
            SessionsMediumScreen(modifier = Modifier)
        }

        WindowWidthSizeClass.Expanded -> {
            SessionsExpandedScreen(modifier = Modifier)
        }
    }
}

@Composable
fun SessionsExpandedScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun SessionsMediumScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun SessionsCompactScreen(
    navController: NavHostController,
    modifier: Modifier,
    onClickLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                navHostController = navController,
                title = stringResource(R.string.sessions),
                showNavigateUpIcon = false
            )
        },
        bottomBar = { BottomNavBar(navHostController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(RegisterSessionScreen) },
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_add_24),
                        contentDescription = stringResource(R.string.add_new_session)
                    )
                },
                shape = MaterialTheme.shapes.medium
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) { }
    }
}
