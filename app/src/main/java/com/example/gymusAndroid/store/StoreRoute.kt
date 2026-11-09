package com.example.gymusAndroid.store

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoreRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            StoreCompactScreen(
                onClickLogout = loginViewModel::logout,
                navController = navController,
                modifier = Modifier
            )
        }

        WindowWidthSizeClass.Medium -> {
            StoreMediumScreen(modifier = Modifier)
        }

        WindowWidthSizeClass.Expanded -> {
            StoreExpandedScreen(modifier = Modifier)
        }

    }
}

@Composable
fun StoreExpandedScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun StoreMediumScreen(modifier: Modifier) {
    TODO("Not yet implemented")
}

@OptIn(
    ExperimentalGridApi::class, ExperimentalFoundationStyleApi::class,
    ExperimentalFlexBoxApi::class
)
@Composable
fun StoreCompactScreen(
    navController: NavHostController,
    modifier: Modifier,
    onClickLogout: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState =
            rememberUpdatedStyleState(interactionSource = interactionSource) {
                it.isEnabled = true
            }
    val style = Style {

    }

    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                navHostController = navController,
                title = "Store",
                isActualScreen = true
            )
        },
        bottomBar = { BottomNavBar(navHostController = navController) }
    ) { innerPadding ->
        Grid(
            config = {
                column(1.fr)
                column(1.fr)
                row(1.fr)
                row(1.fr)
                columnGap(16.dp)
                rowGap(16.dp)
            },
            modifier = Modifier
                .padding(innerPadding)
                .styleable(styleState = styleState, style = style)
        ) {
            Card(modifier = Modifier.fillMaxSize()) { Text("Card 1") }
            Card(modifier = Modifier.fillMaxSize()) { Text("Card 1") }
            Card(modifier = Modifier.fillMaxSize()) { Text("Card 1") }
            Card(modifier = Modifier.fillMaxSize()) { Text("Card 1") }
        }
    }
}