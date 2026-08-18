package com.example.gymusAndroid.core.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.gymusAndroid.core.navigation.bottomNavItems


@Composable
fun BottomNavBar(navController: NavHostController) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        bottomNavItems.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
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
