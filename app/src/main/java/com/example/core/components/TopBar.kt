@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.core.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.gymusAndroid.R

/**
 * @param showNavigateUpIcon show the back arrow if the actual screen
 */
@Composable
fun TopBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onClickLogout: () -> Unit = {},
    title: String = "",
    showNavigateUpIcon: Boolean = false,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    TopAppBar(
        modifier = Modifier,
        navigationIcon = {
            if (showNavigateUpIcon) {
                IconButton(
                    onClick = { navHostController.popBackStack() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = stringResource(R.string.return_back)
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onClickLogout()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_logout_24),
                    contentDescription = stringResource(R.string.logout)
                )
            }
        },
        title = { Text(title, style = MaterialTheme.typography.titleLarge) }
    )
}