package com.example.gymusAndroid.membership.presentation.Memberships

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.example.core.components.BottomNavBar
import com.example.core.components.ShowProgressIndicator
import com.example.core.components.TopBar
import com.example.core.helpers.BASE_URL
import com.example.core.navigation.MemberCardScreen
import com.example.core.navigation.RegisterMembershipScreen
import com.example.core.navigation.UpdateMembershipScreen
import com.example.core.theme.GymusAppTheme
import com.example.gymusAndroid.R
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.membership.domain.models.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MembershipsRoute(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    membershipsViewModel: MembershipsViewModel = koinViewModel(),
    loginViewModel: LoginViewModel = koinViewModel(),
) {
    val uiState by membershipsViewModel.uiState.collectAsState()

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MembershipsCompactScreen(
                isLoading = uiState.isLoading,
                onClickLogout = loginViewModel::logout,
                members = uiState.members,
                modifier = Modifier,
                navController = navController
            )
        }

        WindowWidthSizeClass.Medium -> {
            MembershipsMediumScreen()
        }

        WindowWidthSizeClass.Expanded -> {
            MembershipsExpandedScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembershipsCompactScreen(
    isLoading: Boolean,
    members: Flow<PagingData<Member>>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onClickLogout: () -> Unit,
    imageLoader: ImageLoader = koinInject(),
) {
    val lazyMembersItems = members.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                title = stringResource(R.string.members),
                navHostController = navController,
                showNavigateUpIcon = false
            )
        },
        bottomBar = {
            BottomNavBar(
                navHostController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(RegisterMembershipScreen) },
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = stringResource(R.string.add_member),
                    modifier = Modifier
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
        ) {
            item {
                ShowProgressIndicator(
                    isLoading = isLoading,
                    modifier = Modifier.size(dimensionResource(R.dimen.large)),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            items(
                count = lazyMembersItems.itemCount,
                key = lazyMembersItems.itemKey { it.id }
            ) { index ->
                lazyMembersItems[index]?.let { member ->
                    ListItem(
                        modifier = Modifier
                            .padding(
                                vertical = dimensionResource(R.dimen.small),
                                horizontal = dimensionResource(R.dimen.medium)
                            )
                            .clickable {
                                navController.navigate(
                                    route = MemberCardScreen(
                                        member.id
                                    )
                                )
                            }
                            .clip(MaterialTheme.shapes.medium),
                        colors = ListItemDefaults.colors(
                            headlineColor = Color.Black,
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                            supportingColor = Color.Gray,
                            trailingIconColor = Color.DarkGray
                        ),
                        headlineContent = {
                            Text(
                                text = stringResource(
                                    R.string.full_name,
                                    member.firstName,
                                    member.lastName
                                ),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        },
                        supportingContent = {
                            Text(
                                text = member.endDate,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        leadingContent = {
                            AsyncImage(
                                model = "$BASE_URL/files/download/${member.personalPhoto}",
                                contentDescription = stringResource(R.string.profile_photo),
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop,
                                imageLoader = imageLoader,
                                placeholder = painterResource(R.drawable.baseline_data_thresholding_24),
                                error = painterResource(R.drawable.baseline_error_24)
                            )
                        },
                        trailingContent = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(
                                    space = dimensionResource(R.dimen.medium)
                                )
                            ) {
                                Text(
                                    text = if (member.isActive) stringResource(R.string.active) else stringResource(
                                        R.string.expired
                                    ),
                                    modifier = Modifier
                                )
                                IconButton(
                                    onClick = {
                                        navController.navigate(
                                            UpdateMembershipScreen(
                                                memberId = member.id
                                            )
                                        )
                                    },
                                    modifier = Modifier.size(dimensionResource(R.dimen.medium))
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_edit_24),
                                        contentDescription = stringResource(R.string.update_member)
                                    )
                                }
                            }
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun MembershipsMediumScreen() {
}

@Composable
fun MembershipsExpandedScreen() {
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MembershipsCompactScreenPreview() {
    GymusAppTheme {
        MembershipsCompactScreen(
            isLoading = false,
            members = emptyFlow(),
            navController = rememberNavController(),
            onClickLogout = {}
        )
    }
}