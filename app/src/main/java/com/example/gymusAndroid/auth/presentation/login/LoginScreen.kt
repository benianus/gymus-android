package com.example.gymusAndroid.auth.presentation.login

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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
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
import com.example.core.components.ShowProgressIndicator
import com.example.core.navigation.RegisterScreen
import com.example.core.theme.GymusAppTheme
import com.example.gymusAndroid.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoute(
    navController: NavHostController,
    widthSizeClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val uiState by loginViewModel.uiState.collectAsState()

    // show alert dialog in case of login failed
    ErrorMessageDialog(
        error = uiState.error,
        onDismissRequest = loginViewModel::onErrorChanged,
        onConfirmation = loginViewModel::onErrorChanged,
        dialogTitle = "Error Happen!",
        icon = painterResource(R.drawable.baseline_error_24)
    )

    // display screen adaptively
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            LoginCompactScreen(
                navController = navController,
                uiState = uiState,
                onUsernameChanged = loginViewModel::onUsernameChanged,
                onPasswordChanged = loginViewModel::onPasswordChanged,
                login = loginViewModel::login
            )
        }

        WindowWidthSizeClass.Medium -> {
            LoginMediumScreen(
                navController = navController,
                uiState = uiState,
                onUsernameChanged = loginViewModel::onUsernameChanged,
                onPasswordChanged = loginViewModel::onPasswordChanged,
                login = loginViewModel::login
            )
        }

        WindowWidthSizeClass.Expanded -> {
            LoginExpandedScreen(
                navController = navController,
                uiState = uiState,
                onUsernameChanged = loginViewModel::onUsernameChanged,
                onPasswordChanged = loginViewModel::onPasswordChanged,
                login = loginViewModel::login
            )
        }
    }
}

@Composable
private fun LoginCompactScreen(
    navController: NavHostController,
    uiState: LoginScreenState,
    onUsernameChanged: (username: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    login: (username: String, password: String) -> Unit,
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
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.medium))
            )
            Text(
                text = stringResource(R.string.welcome_back_please_enter_your_details),
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
                keyboardOptions = KeyboardOptions(
                    showKeyboardOnFocus = true,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = { onPasswordChanged(it) },
                label = { Text(stringResource(R.string.password)) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.enter_you_password),
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                supportingText = { uiState.passwordError?.let { Text(it) } },
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.passwordError != null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go,
                    showKeyboardOnFocus = true,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onGo = { login(uiState.username, uiState.password) }
                ),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            Button(
                onClick = { login(uiState.username, uiState.password) },
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                enabled = uiState.isFormValid && !uiState.isLoading
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.medium),
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.login))
                    ShowProgressIndicator(
                        isLoading = uiState.isLoading,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.large))
                            .padding(start = dimensionResource(R.dimen.medium)),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.you_don_t_have_an_account),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = stringResource(R.string.sing_up),
                    style = MaterialTheme.typography.bodyLarge,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    modifier = Modifier.clickable {
                        navController.navigate(
                            RegisterScreen
                        )
                    }
                )
            }
        }
    }
}


@Composable
fun LoginMediumScreen(
    navController: NavHostController,
    uiState: LoginScreenState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    login: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    TODO("Not yet implemented")
}

@Composable
fun LoginExpandedScreen(
    navController: NavHostController,
    uiState: LoginScreenState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    login: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    TODO("Not yet implemented")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginCompactScreenPreview() {
    GymusAppTheme {
        LoginCompactScreen(
            navController = rememberNavController(),
            uiState = LoginScreenState(),
            onUsernameChanged = {},
            onPasswordChanged = {},
            login = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginMediumScreenPreview() {
    GymusAppTheme {
        LoginMediumScreen(
            navController = rememberNavController(),
            uiState = LoginScreenState(),
            onUsernameChanged = {},
            onPasswordChanged = {},
            login = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginExpandedScreenPreview() {
    GymusAppTheme {
        LoginExpandedScreen(
            navController = rememberNavController(),
            uiState = LoginScreenState(),
            onUsernameChanged = {},
            onPasswordChanged = {},
            login = { _, _ -> }
        )
    }
}