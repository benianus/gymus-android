package com.example.gymusAndroid.auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gymusAndroid.R
import com.example.gymusAndroid.core.components.ShowErrorMessage
import com.example.gymusAndroid.core.navigation.Login
import com.example.gymusAndroid.core.theme.GymusAppTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegisterRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = koinViewModel<RegisterViewModel>(),
    widthSizeClass: WindowWidthSizeClass
) {
    val uiState by registerViewModel.uiState.collectAsState()
    RegisterScreen(
        navController,
        uiState,
        registerViewModel::register,
        registerViewModel::onUsernameChanged,
        registerViewModel::onPasswordChanged,
        registerViewModel::onPasswordConfirmationChanged
    )
}

@Composable
private fun RegisterScreen(
    navController: NavHostController,
    uiState: RegisterScreenState,
    register: (String, String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmationChanged: (String) -> Unit,
) {
    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Sing up",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium))
            )
            Text(
                text = "Welcome! Register a new account.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium))
            )
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { onUsernameChanged(it) },
                label = { Text("Username") },
                placeholder = {
                    Text(
                        text = "Enter you username",
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                isError = uiState.usernameError != null,
                supportingText = { uiState.usernameError?.let { Text(it) } }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { onPasswordChanged(it) },
                label = { Text("Password") },
                placeholder = { Text("Enter you password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.passwordError != null,
                supportingText = { uiState.passwordError?.let { Text(it) } }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = uiState.passwordConfirmation,
                onValueChange = { onPasswordConfirmationChanged(it) },
                label = { Text("Password Confirmation") },
                placeholder = { Text("Confirm your password") },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.passwordConfirmationError != null,
                supportingText = { uiState.passwordConfirmationError?.let { Text(it) } }
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            Button(
                onClick = { register(uiState.username, uiState.password) },
                shape = MaterialTheme.shapes.medium,
                enabled = uiState.isFormValid
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Sing up")
                    if (uiState.loading) CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.large))
                            .padding(start = dimensionResource(R.dimen.medium))
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "You Already have an account! ",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(
                                Login
                            )
                        }
                )
            }

        }
    }

    ShowErrorMessage(uiState.error)
}

@Preview
@Composable
fun RegisterRoutePreview() {
    GymusAppTheme {
        RegisterScreen(
            navController = rememberNavController(),
            uiState = RegisterScreenState(),
            register = { _, _ -> },
            onUsernameChanged = {},
            onPasswordChanged = {},
            onPasswordConfirmationChanged = {}
        )
    }
}