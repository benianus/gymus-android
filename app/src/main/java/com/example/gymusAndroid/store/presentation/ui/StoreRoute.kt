package com.example.gymusAndroid.store.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalFlexBoxApi
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.contentPadding
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.core.components.BottomNavBar
import com.example.core.components.TopBar
import com.example.core.navigation.AddProductScreen
import com.example.core.navigation.ProductsScreen
import com.example.gymusAndroid.R
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun StoreRoute(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
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
    val styleState = rememberUpdatedStyleState(
        interactionSource = interactionSource
    ) {
        it.isEnabled = true
    }
    val style = Style {
        contentPadding(PaddingValues(16.dp))
    }

    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                navHostController = navController,
                title = "Store",
                showNavigateUpIcon = false
            )
        },
        bottomBar = { BottomNavBar(navHostController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AddProductScreen) },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "add member",
                    modifier = Modifier
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Grid(
            config = {
                column(1.fr)
                column(1.fr)
                row(1.fr)
                row(1.fr)
                row(1.fr)
                columnGap(16.dp)
                rowGap(16.dp)
            },
            modifier = Modifier
                .padding(innerPadding)
                .styleable(styleState = styleState, style = style)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { navController.navigate(ProductsScreen) }
            ) {
                Text(
                    text = "Card 1",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}