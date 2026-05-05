package com.examdple.gymus_android.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.examdple.gymus_android.auth.presentation.AuthViewModel
import com.examdple.gymus_android.auth.presentation.LoginScreen
import com.examdple.gymus_android.auth.presentation.RegisterScreen
import com.examdple.gymus_android.dashboard.presentation.DashboardScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = koinViewModel()
) {
    val isAuthenticated by authViewModel.authenticated.collectAsState()

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated.isBlank()) {
            navController.navigate(Login) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(Dashboard) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated.isBlank()) Login else Dashboard
    ) {
        composable<Login> { LoginScreen() }

        composable<Register> { RegisterScreen() }

        composable<Dashboard> { DashboardScreen() }

        composable<Memberships> { }

        composable<RegisterMembership> { }

        composable<MemberCard> { }

        composable<Sessions> { }

        composable<RegisterSession> { }

        composable<Products> { }

        composable<Reports> { }
    }
}