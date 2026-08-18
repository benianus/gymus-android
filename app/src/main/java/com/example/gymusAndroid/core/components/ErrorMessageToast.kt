package com.example.gymusAndroid.core.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowErrorMessage(error: String?) {
    error?.let { errorMessage ->
        Toast.makeText(
            LocalContext.current,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}