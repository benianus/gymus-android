package com.example.gymusAndroid.membership.presentation.MembershipRegister

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
fun RegisterMembershipRoute(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            RegisterMembershipCompactScreen(
                onClickLogout = loginViewModel::logout,
                navController = navController,
                modifier = Modifier
            )
        }

        WindowWidthSizeClass.Medium -> {
            RegisterMembershipMediumScreen(modifier = Modifier)
        }

        WindowWidthSizeClass.Expanded -> {
            RegisterMembershipExpandedScreen(modifier = Modifier)
        }
    }
}

@Composable
fun RegisterMembershipExpandedScreen(modifier: Modifier = Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun RegisterMembershipMediumScreen(modifier: Modifier = Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun RegisterMembershipCompactScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onClickLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                title = stringResource(R.string.register_membership),
                navHostController = navController,
                showNavigateUpIcon = true
            )
        },
        bottomBar = {
            BottomNavBar(navHostController = navController)
        }
    ) { innerPadding ->
        Text("Register Membership", modifier = modifier.padding(innerPadding))
    }
}