package com.example.gymusAndroid.session

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.gymusAndroid.R
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar
import com.example.gymusAndroid.core.navigation.RegisterSessionScreen

@Composable
fun SessionsRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            SessionsCompactScreen(
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
fun SessionsExpandedScreen(modifier: Modifier.Companion) {
    TODO("Not yet implemented")
}

@Composable
fun SessionsMediumScreen(modifier: Modifier.Companion) {
    TODO("Not yet implemented")
}

@Composable
fun SessionsCompactScreen(
    navController: NavHostController,
    modifier: Modifier.Companion
) {
    Scaffold(
        topBar = {
            TopBar(
                navHostController = navController,
                title = "Sessions",
                isActualScreen = true
            )
        },
        bottomBar = { BottomNavBar(navHostController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(RegisterSessionScreen) },
                content = {
                    Icon(
                        painter = painterResource(R.drawable.baseline_add_24),
                        contentDescription = "Add"
                    )
                },
                shape = MaterialTheme.shapes.medium
            )
        }
    ) { }
}
