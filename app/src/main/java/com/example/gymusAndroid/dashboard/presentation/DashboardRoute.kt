package com.example.gymusAndroid.dashboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.example.core.components.BottomNavBar
import com.example.core.components.ShowProgressIndicator
import com.example.core.components.TopBar
import com.example.core.helpers.BASE_URL
import com.example.core.navigation.MemberCardScreen
import com.example.core.navigation.MembershipsScreen
import com.example.core.navigation.ProductsScreen
import com.example.core.navigation.RegisterMembershipScreen
import com.example.core.navigation.RegisterSessionScreen
import com.example.core.navigation.ReportsScreen
import com.example.core.navigation.SessionsScreen
import com.example.core.theme.GymusAppTheme
import com.example.gymusAndroid.R
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.dashboard.domain.models.Member
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardRoute(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    dashboardViewModel: DashboardViewModel = koinViewModel(),
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val uiState = dashboardViewModel.uiState.collectAsState().value


    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            DashboardCompactScreen(
                onClickLogout = {
                    loginViewModel.logout()
                },
                navController = navController,
                uiState = uiState
            )
        }

        WindowWidthSizeClass.Medium -> {
            DashboardMediumScreen(navController = navController)
        }

        WindowWidthSizeClass.Expanded -> {
            DashboardExpandedScreen(navController = navController)
        }
    }
}

@Composable
fun DashboardExpandedScreen(navController: NavHostController) {
    Text("Expanded")
}

@Composable
fun DashboardMediumScreen(navController: NavHostController) {
    Text("Medium")
}

@Composable
fun DashboardCompactScreen(
    onClickLogout: () -> Unit,
    uiState: DashboardScreenState,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                navHostController = navController,
                title = stringResource(R.string.app_name),
                showNavigateUpIcon = false
            )
        },
        bottomBar = { BottomNavBar(navController) },
    ) { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(R.dimen.medium))
        ) {
            item {
                ShowProgressIndicator(
                    isLoading = uiState.isLoading,
                    modifier = Modifier.size(dimensionResource(R.dimen.large)),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            item {
                Text(
                    text = stringResource(R.string.hello, uiState.role ?: ""),
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            item {
                Text(
                    text = stringResource(R.string.welcome_back_to_your_dashboard_today),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            item {
                SummaryItems(
                    uiState = uiState,
                    modifier = Modifier
                )
            }
            item { QuickAccessItems(navController = navController) }
            item {
                RecentCheckedInMembers(
                    navHostController = navController,
                    members = uiState.members,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun RecentCheckedInMembers(
    navHostController: NavHostController,
    members: Set<Member>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(id = R.dimen.small)
        )
    ) {
        Text(
            text = stringResource(R.string.recent_check_ins),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        if (members.isNotEmpty()) {
            for (member in members) {
                RecentCheckedMemberItem(
                    onClickRecentMember = { memberId ->
                        navHostController.navigate(MemberCardScreen(memberId))
                    },
                    member = member,
                    modifier = Modifier
                )
            }
        } else {
            Text(
                text = stringResource(R.string.no_recent_check_ins),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

    }
}

@Composable
private fun RecentCheckedMemberItem(
    onClickRecentMember: (memberId: Int) -> Unit,
    member: Member,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = koinInject()
) {
    ListItem(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClickRecentMember(member.id) },
        colors = ListItemDefaults.colors(
            headlineColor = Color.Black,
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            supportingColor = Color.Gray,
            trailingIconColor = Color.DarkGray
        ),
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
        headlineContent = {
            Text(
                text = member.fullName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        supportingContent = {
            Text(
                text = stringResource(R.string.member, member.status),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Normal
            )
        },
        trailingContent = { Text(text = member.checkedInAt) }
    )
}

@Composable
private fun SummaryItems(
    uiState: DashboardScreenState,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.medium)
        )
    ) {
        SummaryItem(
            stringResource(R.string.active_members),
            "${uiState.activeMembers}",
            Modifier.weight(1f)
        )
        SummaryItem(
            stringResource(R.string.today_s_revenue),
            "${uiState.todayRevenue}",
            Modifier.weight(1f)
        )
    }
}

@Composable
private fun SummaryItem(
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.medium))) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = body,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Composable
private fun QuickAccessItems(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.small)
        ),
    ) {
        Text(
            text = stringResource(R.string.quick_access),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensionResource(
                    R.dimen.medium
                )
            )
        ) {
            QuickAccessItem(
                {
                    navController.navigate(MembershipsScreen)
                },
                R.drawable.baseline_person_24,
                stringResource(R.string.members),
                stringResource(R.string.memberships),
                modifier.weight(1f)
            )
            QuickAccessItem(
                {
                    navController.navigate(SessionsScreen)
                },
                R.drawable.baseline_access_time_filled_24,
                stringResource(R.string.sessions),
                stringResource(R.string.sessions),
                modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensionResource(
                    R.dimen.medium
                )
            )
        ) {
            QuickAccessItem(
                { navController.navigate(ProductsScreen) },
                icon = R.drawable.baseline_store_24,
                title = stringResource(R.string.store),
                contentDescription = stringResource(R.string.store),
                modifier = modifier.weight(1f)
            )
            QuickAccessItem(
                { navController.navigate(ReportsScreen) },
                R.drawable.baseline_assessment_24,
                stringResource(R.string.reports),
                stringResource(R.string.reports),
                modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensionResource(
                    R.dimen.medium
                )
            )
        ) {
            QuickAccessItem(
                { navController.navigate(RegisterMembershipScreen) },
                icon = R.drawable.baseline_person_add_24,
                title = stringResource(R.string.register_member),
                contentDescription = stringResource(R.string.register_member),
                modifier = modifier.weight(1f)
            )
            QuickAccessItem(
                { navController.navigate(RegisterSessionScreen) },
                R.drawable.baseline_more_time_24,
                stringResource(R.string.register_session),
                stringResource(R.string.register_session),
                modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun QuickAccessItem(
    navigateTo: () -> Unit,
    icon: Int,
    title: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { navigateTo() },
    ) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.medium))) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.small)))
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DashboardCompactScreenPreview() {
    GymusAppTheme {
        DashboardCompactScreen(
            onClickLogout = {},
            uiState = DashboardScreenState(),
            navController = rememberNavController()
        )
    }
}