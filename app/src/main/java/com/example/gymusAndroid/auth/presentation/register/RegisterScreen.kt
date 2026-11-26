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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.core.components.ErrorMessageDialog
import com.example.core.navigation.LoginScreen
import com.example.core.theme.GymusAppTheme
import com.example.gymusAndroid.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun RegisterRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = koinViewModel(),
    widthSizeClass: WindowWidthSizeClass
) {
    val uiState by registerViewModel.uiState.collectAsState()

    // show error alert dialog in case registration failed
    ErrorMessageDialog(
        error = uiState.error,
        onDismissRequest = registerViewModel::onErrorChanged,
        onConfirmation = registerViewModel::onErrorChanged,
        dialogTitle = "Error Happen!",
    )

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            RegisterCompactScreen(
                navController,
                uiState,
                registerViewModel::register,
                registerViewModel::onUsernameChanged,
                registerViewModel::onPasswordChanged,
                registerViewModel::onPasswordConfirmationChanged
            )
        }

        WindowWidthSizeClass.Medium -> {
            RegisterMediumScreen(
                navController,
                uiState,
                registerViewModel::register,
                registerViewModel::onUsernameChanged,
                registerViewModel::onPasswordChanged,
                registerViewModel::onPasswordConfirmationChanged
            )
        }

        WindowWidthSizeClass.Expanded -> {
            RegisterExpandedScreen(
                navController,
                uiState,
                registerViewModel::register,
                registerViewModel::onUsernameChanged,
                registerViewModel::onPasswordChanged,
                registerViewModel::onPasswordConfirmationChanged
            )
        }
    }

}

@Composable
private fun RegisterCompactScreen(
    navController: NavHostController,
    uiState: RegisterScreenState,
    register: (username: String, password: String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmationChanged: (String) -> Unit,
    modifier: Modifier = Modifier
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
                text = stringResource(R.string.sing_up),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium))
            )
            Text(
                text = stringResource(R.string.welcome_register_a_new_account),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium))
            )
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { onUsernameChanged(it) },
                label = { Text(stringResource(R.string.username)) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_you_username),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                isError = uiState.usernameError != null,
                supportingText = { uiState.usernameError?.let { Text(it) } },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    showKeyboardOnFocus = true,
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { onPasswordChanged(it) },
                label = { Text(stringResource(R.string.password)) },
                placeholder = { Text(stringResource(R.string.enter_you_password)) },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.passwordError != null,
                supportingText = { uiState.passwordError?.let { Text(it) } },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    showKeyboardOnFocus = true,
                    keyboardType = KeyboardType.Password
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = uiState.passwordConfirmation,
                onValueChange = { onPasswordConfirmationChanged(it) },
                label = { Text(stringResource(R.string.password_confirmation)) },
                placeholder = { Text(stringResource(R.string.confirm_your_password)) },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.passwordConfirmationError != null,
                supportingText = {
                    uiState.passwordConfirmationError?.let { Text(it) }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go,
                    showKeyboardOnFocus = true,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onGo = {
                        register(
                            uiState.username,
                            uiState.password
                        )
                    }
                )
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            Button(
                onClick = { register(uiState.username, uiState.password) },
                shape = MaterialTheme.shapes.medium,
                enabled = uiState.isFormValid && !uiState.isLoading
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.sing_up))
                    if (uiState.isLoading) CircularProgressIndicator(
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
                    text = stringResource(R.string.you_already_have_an_account),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(
                                LoginScreen
                            )
                        }
                )
            }

        }
    }

}

@Composable
private fun RegisterMediumScreen(
    navController: NavHostController,
    uiState: RegisterScreenState,
    register: (String, String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmationChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Medium Screen")
        }
    }
}

@Composable
private fun RegisterExpandedScreen(
    navController: NavHostController,
    uiState: RegisterScreenState,
    register: (String, String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmationChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text("Medium Screen")
        }
    }
}

@Preview
@Composable
fun RegisterCompactRoutePreview() {
    GymusAppTheme {
        RegisterCompactScreen(
            navController = rememberNavController(),
            uiState = RegisterScreenState(),
            register = { _, _ -> },
            onUsernameChanged = {},
            onPasswordChanged = {},
            onPasswordConfirmationChanged = {}
        )
    }
}