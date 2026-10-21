package com.example.gymusAndroid.core.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymusAndroid.core.navigation.bottomNavItems

@Composable
fun BottomNavBar(navHostController: NavHostController) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination

    var selectedDestination by rememberSaveable { mutableStateOf(0) }

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        bottomNavItems.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = currentRoute?.hasRoute(destination.route::class) == true,
                onClick = {
                    navHostController.navigate(destination.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    selectedDestination = index
                },
                icon = {
                    Icon(
                        painterResource(destination.icon),
                        contentDescription = destination.contentDescription
                    )
                },
                label = { Text(destination.label) }
            )
        }
    }
}
