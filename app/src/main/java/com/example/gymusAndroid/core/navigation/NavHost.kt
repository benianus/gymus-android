package com.example.gymusAndroid.core.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gymusAndroid.auth.presentation.login.LoginRoute
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.auth.presentation.register.RegisterRoute
import com.example.gymusAndroid.dashboard.presentation.DashboardRoute
import com.example.gymusAndroid.membership.presentation.MembershipCard.MemberCardRoute
import com.example.gymusAndroid.membership.presentation.MembershipRegister.RegisterMembershipRoute
import com.example.gymusAndroid.membership.presentation.Memberships.MembershipsRoute
import com.example.gymusAndroid.reports.ReportsRoute
import com.example.gymusAndroid.session.RegisterSessionRoute
import com.example.gymusAndroid.session.SessionsRoute
import com.example.gymusAndroid.store.StoreRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
) {
    val isAuthenticated by loginViewModel.authenticated.collectAsState()

//    LaunchedEffect(isAuthenticated) {
//        if (isAuthenticated.isBlank()) {
//            navController.navigate(Login) { popUpTo(0) { inclusive = true } }
//        } else {
//            navController.navigate(Dashboard) { popUpTo(0) { inclusive = true } }
//        }
//    }

    // TODO("make 'if (isAuthenticated.isBlank()) Login else Dashboard' in the start destination later")

    NavHost(
        navController = navController,
        startDestination = Dashboard
    ) {
        composable<Login> {
            LoginRoute(
                navController = navController,
                widthSizeClass = widthSizeClass
            )
        }

        composable<Register> {
            RegisterRoute(
                navController = navController,
                widthSizeClass = widthSizeClass
            )
        }

        composable<Dashboard> {
            DashboardRoute(
                navController = navController,
                widthSizeClass = widthSizeClass
            )
        }

        composable<Memberships> {
            MembershipsRoute(
                navController = navController,
                widthSizeClass = widthSizeClass
            )
        }

        composable<RegisterMembership> {
            RegisterMembershipRoute(
                widthSizeClass = widthSizeClass,
                navController = navController
            )
        }

        composable<MemberCard> {
            MemberCardRoute(
                widthSizeClass = widthSizeClass,
                navHostController = navController
            )
        }

        composable<Sessions> {
            SessionsRoute(
                widthSizeClass = widthSizeClass,
                navController = navController
            )
        }

        composable<RegisterSession> {
            RegisterSessionRoute(
                widthSizeClass = widthSizeClass,
                navController = navController
            )
        }

        composable<Products> {
            StoreRoute(
                widthSizeClass = widthSizeClass,
                navController = navController
            )
        }

        composable<Reports> {
            ReportsRoute(
                widthSizeClass = widthSizeClass,
                navController = navController
            )
        }
    }
}