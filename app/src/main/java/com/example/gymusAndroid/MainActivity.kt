@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.example.gymusAndroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.rememberNavController
import com.example.gymusAndroid.core.navigation.AppNavHost
import com.example.gymusAndroid.core.theme.GymusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymusAppTheme {
                val widthSizeClass =
                    calculateWindowSizeClass(this).widthSizeClass

                val navController = rememberNavController()
                AppNavHost(navController = navController, widthSizeClass = widthSizeClass)
            }
        }
    }
}
