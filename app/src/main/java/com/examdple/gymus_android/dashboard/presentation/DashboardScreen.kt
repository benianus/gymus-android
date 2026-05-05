package com.examdple.gymus_android.dashboard.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DashboardScreen(modifier: Modifier = Modifier) {
    Scaffold {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Hello, Dashboard")
        }
    }
}