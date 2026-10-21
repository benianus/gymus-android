package com.example.gymusAndroid.membership.presentation.MembershipCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gymusAndroid.R
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar
import com.example.gymusAndroid.core.navigation.MemberCard

@Composable
fun MemberCardRoute(
    widthSizeClass: WindowWidthSizeClass,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MemberCardCompactScreen(navHostController = navHostController, modifier = Modifier)
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
fun MemberCardCompactScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val isMemberCardScreen = backStackEntry?.destination?.hasRoute<MemberCard>() == true
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
        Card(
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .padding(all = dimensionResource(R.dimen.medium))
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(all = dimensionResource(R.dimen.medium))
            ) {
                Row {
                    Image(
                        painter = painterResource(R.drawable.baseline_person_24),
                        contentDescription = "member card photo"
                    )
                    Column {
                        Text("Mohamed BENIANE")
                        Text("Birthdate: 1994-02-11")
                        Text("Join Date: 2022-02-11")
                        Text("End Date: 2023-02-11")
                    }
                }
                Column {
                    Button(
                        onClick = { TODO() }
                    ) {
                        Text("Check in")
                    }
                    Button(
                        onClick = { TODO() }
                    ) {
                        Text("Renew Membership")
                    }
                }
            }
        }
    }
}

@Composable
fun MemberCardMediumScreen(modifier: Modifier = Modifier) {

}

@Composable
fun MemberCardExpandedScreen(modifier: Modifier = Modifier) {

}