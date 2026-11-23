package com.example.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.gymusAndroid.R

@Composable
fun ErrorMessageDialog(
    error: String?,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = stringResource(R.string.error_happen),
    icon: Painter = painterResource(R.drawable.baseline_error_24)
) {
    error?.let { errorMessage ->
        AlertDialog(
            icon = {
                Icon(
                    painter = icon,
                    contentDescription = stringResource(R.string.error_icon)
                )
            },
            title = { Text(text = dialogTitle) },
            text = { Text(text = errorMessage) },
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(stringResource(R.string.close))
                }
            },
            modifier = Modifier,
            shape = MaterialTheme.shapes.medium
        )
    }
}