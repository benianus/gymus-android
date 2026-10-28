package com.example.gymusAndroid.membership.presentation.MembershipCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymusAndroid.R
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.ItemNotFoundErrorMessage
import com.example.gymusAndroid.core.components.ShowErrorMessage
import com.example.gymusAndroid.core.components.TopBar
import com.example.gymusAndroid.core.navigation.MemberCardScreen
import com.example.gymusAndroid.membership.domain.models.MemberCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemberCardRoute(
    widthSizeClass: WindowWidthSizeClass,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    memberCardViewModel: MemberCardViewModel = koinViewModel()
) {
    val uiState by memberCardViewModel.uiState.collectAsState()
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (uiState.loading) CircularProgressIndicator()
            uiState.error?.let { ShowErrorMessage(it) }
            MemberCardCompactScreen(
                memberCard = uiState.memberCard,
                navHostController = navHostController,
                modifier = Modifier
            )
        }

        WindowWidthSizeClass.Medium -> {
            MemberCardMediumScreen(modifier = Modifier)
        }

        WindowWidthSizeClass.Expanded -> {
            MemberCardExpandedScreen(modifier = Modifier)
        }
    }
}

@Composable
fun MemberCardCompactScreen(
    memberCard: MemberCard?,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val isMemberCardScreen =
            backStackEntry?.destination?.hasRoute<MemberCardScreen>() == true
    Scaffold(
        topBar = {
            TopBar(
                title = "Member Card",
                navHostController = navHostController,
                isActualScreen = false
            )
        },
        bottomBar = {
            BottomNavBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        if (memberCard != null) {
            MemberCardItem(innerPadding, memberCard)
        } else {
            ItemNotFoundErrorMessage(modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun MemberCardItem(
    innerPadding: PaddingValues,
    memberCard: MemberCard
) {
    Card(
        modifier = Modifier
            .padding(paddingValues = innerPadding)
            .padding(all = dimensionResource(R.dimen.medium))
            .fillMaxSize(),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(all = dimensionResource(R.dimen.medium))
        ) {
            Row(
                modifier = Modifier.weight(2f)
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_person_24),
                    contentDescription = "member card photo",
                    modifier = Modifier
                        .size(25.dp)
                        .weight(1f)
                        .background(color = Color.Red),
                    alignment = Alignment.Center
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("${memberCard.firstName} ${memberCard.lastName}")
                    Text("Birthdate: ${memberCard.birthDate}")
                    Text("Join Date: ${memberCard.joinDate}")
                    Text("End Date: ${memberCard.endDate}")
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
            ) {
                Button(
                    onClick = { TODO() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text("Check-In")
                }
                Button(
                    onClick = { TODO() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Renew Membership")
                }
            }
        }
    }
}

@Composable
fun MemberCardMediumScreen(modifier: Modifier = Modifier) {
    Text("Medium Screen")
}

@Composable
fun MemberCardExpandedScreen(modifier: Modifier = Modifier) {
    Text("ExpandedScreen")
}