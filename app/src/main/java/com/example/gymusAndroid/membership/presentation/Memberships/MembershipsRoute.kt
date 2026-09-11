package com.example.gymusAndroid.membership.presentation.Memberships

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gymusAndroid.R
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar
import com.example.gymusAndroid.core.navigation.MemberCard
import com.example.gymusAndroid.core.navigation.Memberships
import com.example.gymusAndroid.core.navigation.RegisterMembership
import com.example.gymusAndroid.core.theme.GymusAppTheme
import com.example.gymusAndroid.membership.domain.models.Member
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MembershipsRoute(
    widthSizeClass: WindowWidthSizeClass,
    navController: NavHostController,
    membershipsViewModel: MembershipsViewModel = koinViewModel<MembershipsViewModel>(),
    modifier: Modifier = Modifier
) {
    val uiState by membershipsViewModel.uiState.collectAsState()

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MembershipsCompactScreen(
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
    members: List<Member>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val isMembershipScreen = backStackEntry?.destination?.hasRoute<Memberships>() ?: false

    var expanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(
                navHostController = navController,
                isActualScreen = isMembershipScreen
            )

        },
        bottomBar = {
            BottomNavBar(
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(RegisterMembership) },
                modifier = Modifier.size(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = "add member",
                    modifier = Modifier
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(R.dimen.small)),
            modifier = Modifier.padding(innerPadding),
        ) {
            items(members) { member ->
                Card(
                    modifier = Modifier
                        .padding(bottom = dimensionResource(R.dimen.medium))
                        .clickable {
                            navController.navigate(
                                MemberCard(member.id)
                            )
                        },
                    border = BorderStroke(1.dp, Color.LightGray),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.medium))
                    ) {
                        Image(
                            painter = painterResource(R.drawable.baseline_person_24),
                            contentDescription = "profile photo",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small)))
                        Column(
                            modifier = Modifier.weight(2f),
                        ) {
                            Text(
                                text = "${member.firstName} ${member.lastName}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = member.endDate,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Text(
                            text = if (member.isActive) "Active" else "Expired",
                            modifier = Modifier
                        )
                    }
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
        MembershipsCompactScreen(members = emptyList(), navController = rememberNavController())
    }
}