package com.example.gymusAndroid.store.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ProductDetailsRoute(
    windowWidthSizeClass: WindowWidthSizeClass,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Text("Product Details")
}