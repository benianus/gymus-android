package com.example.gymusAndroid.membership.presentation.MembershipRegister

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar

@Composable
fun RegisterMembershipRoute(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            RegisterMembershipCompactScreen(
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
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Register Membership",
                navHostController = navController,
                isActualScreen = false
            )
        },
        bottomBar = {
            BottomNavBar(navHostController = navController)
        }
    ) { innerPadding ->
        Text("Register Membership", modifier = modifier.padding(innerPadding))
    }
}