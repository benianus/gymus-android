@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.gymusAndroid.core.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.gymusAndroid.R

/**
 * @param isActualScreen show the back arrow if the actual screen
 */
@Composable
fun TopBar(
    onClickLogout: () -> Unit = {},
    navHostController: NavHostController,
    title: String = "",
    isActualScreen: Boolean = false
) {
    val context = LocalContext.current

    TopAppBar(
        navigationIcon = {
            if (!isActualScreen) {
                IconButton(
                    onClick = { navHostController.navigateUp() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = "return back"
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { onClickLogout() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_logout_24),
                    contentDescription = "logout"
                )
            }


        },
        title = { Text(title, style = MaterialTheme.typography.titleLarge) }
    )
}