package com.example.gymusAndroid.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.gymusAndroid.membership.MemberCardRoute
import com.example.gymusAndroid.membership.MembershipsRoute
import com.example.gymusAndroid.membership.RegisterMembershipRoute
import com.example.gymusAndroid.reports.ReportsRoute
import com.example.gymusAndroid.session.RegisterSessionRoute
import com.example.gymusAndroid.session.SessionsRoute
import com.example.gymusAndroid.store.StoreRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>()
) {
    val isAuthenticated by loginViewModel.authenticated.collectAsState()

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated.isBlank()) {
            navController.navigate(Login) { popUpTo(0) { inclusive = true } }
        } else {
            navController.navigate(Dashboard) { popUpTo(0) { inclusive = true } }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated.isBlank()) Login else Dashboard
    ) {
        composable<Login> { LoginRoute(navController = navController) }
        composable<Register> { RegisterRoute(navController = navController) }
        composable<Dashboard> { DashboardRoute(navController = navController) }
        composable<Memberships> { MembershipsRoute(navController = navController) }
        composable<RegisterMembership> { RegisterMembershipRoute(navController = navController) }
        composable<MemberCard> { MemberCardRoute(navController = navController) }
        composable<Sessions> { SessionsRoute(navController = navController) }
        composable<RegisterSession> { RegisterSessionRoute(navController = navController) }
        composable<Products> { StoreRoute(navController = navController) }
        composable<Reports> { ReportsRoute(navController = navController) }
    }
}