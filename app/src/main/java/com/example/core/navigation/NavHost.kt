package com.example.core.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
import com.example.gymusAndroid.membership.presentation.MembershipCard.MemberCardRoute
import com.example.gymusAndroid.membership.presentation.MembershipRegister.RegisterMembershipRoute
import com.example.gymusAndroid.membership.presentation.MembershipUpdate.UpdateMembershipRoute
import com.example.gymusAndroid.membership.presentation.Memberships.MembershipsRoute
import com.example.gymusAndroid.reports.presentation.ui.ReportsRoute
import com.example.gymusAndroid.session.presentation.ui.RegisterSessionRoute
import com.example.gymusAndroid.session.presentation.ui.SessionsRoute
import com.example.gymusAndroid.store.presentation.ui.AddProductRoute
import com.example.gymusAndroid.store.presentation.ui.ProductDetailsRoute
import com.example.gymusAndroid.store.presentation.ui.SaleRoute
import com.example.gymusAndroid.store.presentation.ui.StoreRoute
import com.example.gymusAndroid.store.presentation.ui.UpdateProductRoute
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    windowWidthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
) {
    val isAuthenticated by loginViewModel.authenticated.collectAsState()

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated.isBlank()) {
            navHostController.navigate(LoginScreen) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        } else {
            navHostController.navigate(DashboardScreen) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }

    NavHost(
        navController = navHostController,
        startDestination = if (isAuthenticated.isBlank()) LoginScreen else DashboardScreen
    ) {
        composable<LoginScreen> {
            LoginRoute(
                navController = navHostController,
                widthSizeClass = windowWidthSizeClass
            )
        }

        composable<RegisterScreen> {
            RegisterRoute(
                navController = navHostController,
                widthSizeClass = windowWidthSizeClass
            )
        }

        composable<DashboardScreen> {
            DashboardRoute(
                navController = navHostController,
                widthSizeClass = windowWidthSizeClass
            )
        }

        composable<MembershipsScreen> {
            MembershipsRoute(
                widthSizeClass = windowWidthSizeClass,
                navController = navHostController,
            )
        }

        composable<RegisterMembershipScreen> {
            RegisterMembershipRoute(
                widthSizeClass = windowWidthSizeClass,
                navController = navHostController
            )
        }

        composable<MemberCardScreen> {
            MemberCardRoute(
                widthSizeClass = windowWidthSizeClass,
                navHostController = navHostController
            )
        }

        composable<UpdateMembershipScreen> {
            UpdateMembershipRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navHostController = navHostController,
                modifier = Modifier
            )
        }

        composable<SessionsScreen> {
            SessionsRoute(
                widthSizeClass = windowWidthSizeClass,
                navController = navHostController
            )
        }

        composable<RegisterSessionScreen> {
            RegisterSessionRoute(
                widthSizeClass = windowWidthSizeClass,
                navController = navHostController
            )
        }

        composable<ProductsScreen> {
            StoreRoute(
                widthSizeClass = windowWidthSizeClass,
                navController = navHostController
            )
        }

        composable<ProductDetailsScreen> {
            ProductDetailsRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navHostController = navHostController,
                modifier = Modifier
            )
        }

        composable<AddProductScreen> {
            AddProductRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navHostController = navHostController,
                modifier = Modifier
            )
        }

        composable<UpdateProductScreen> {
            UpdateProductRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navHostController = navHostController,
                modifier = Modifier
            )
        }

        composable<SaleScreen> {
            SaleRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navHostController = navHostController,
                modifier = Modifier
            )
        }

        composable<ReportsScreen> {
            ReportsRoute(
                widthSizeClass = windowWidthSizeClass,
                navController = navHostController,
            )
        }
    }
}