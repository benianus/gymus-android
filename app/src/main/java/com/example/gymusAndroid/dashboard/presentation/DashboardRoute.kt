@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalGridApi::class)

package com.example.gymusAndroid.dashboard.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import coil3.compose.AsyncImage
import com.example.gymusAndroid.R
import com.example.gymusAndroid.core.components.BottomNavBar
import com.example.gymusAndroid.core.components.TopBar
import com.example.gymusAndroid.core.navigation.Dashboard
import com.example.gymusAndroid.core.navigation.Memberships
import com.example.gymusAndroid.core.navigation.Products
import com.example.gymusAndroid.core.navigation.RegisterMembership
import com.example.gymusAndroid.core.navigation.RegisterSession
import com.example.gymusAndroid.core.navigation.Reports
import com.example.gymusAndroid.core.navigation.Sessions
import com.example.gymusAndroid.core.theme.GymusAppTheme

@Composable
fun DashboardRoute(navController: NavHostController, modifier: Modifier = Modifier) {
    DashboardScreen(navController = navController)
}

@Composable
fun DashboardScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val isDashboardScreen = backStackEntry?.destination?.hasRoute<Dashboard>() ?: false
    Scaffold(
        topBar = { TopBar(navController, isDashboardScreen) },
        bottomBar = { BottomNavBar(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = dimensionResource(R.dimen.medium))
        ) {
            Text(text = "Hello, Admin", style = MaterialTheme.typography.headlineLarge)
            Text(
                text = "Welcome back to your dashboard today.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            SummaryItems()
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            QuickAccessItems(navController = navController)
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
            RecentCheckedMember()
        }
    }
}

@Composable
fun RecentCheckedMember() {
    Column {
        Text(
            text = "Recent check-ins",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
        Card(
            modifier = Modifier,
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
                        text = "Mohamed BENIANE",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Active member",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Normal
                    )
                }
                Text(text = "10:00 AM", modifier = Modifier)
            }
        }
    }
}

@Composable
private fun SummaryItems(modifier: Modifier = Modifier) {
    Row {
        SummaryItem("Active members", "250", Modifier.weight(1f))
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium)))
        SummaryItem("Today's revenue", "10000 DZD", Modifier.weight(1f))
    }
}

@Composable
private fun SummaryItem(title: String, body: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        elevation = CardDefaults.cardElevation(1.dp)
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
private fun QuickAccessItems(navController: NavHostController, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Quick Access",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
        Row {
            QuickAccessItem(
                { navController.navigate(Memberships) },
                R.drawable.baseline_person_24,
                "Memberships",
                "memberships",
                modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium)))
            QuickAccessItem(
                { navController.navigate(Sessions) },
                R.drawable.baseline_access_time_filled_24,
                "Sessions",
                "sessions",
                modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
        Row {
            QuickAccessItem(
                { navController.navigate(Products) },
                icon = R.drawable.baseline_store_24,
                title = "Store",
                contentDescription = "store",
                modifier = modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium)))
            QuickAccessItem(
                { navController.navigate(Reports) },
                R.drawable.baseline_assessment_24,
                "Reports",
                "reports",
                modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.medium)))
        Row {
            QuickAccessItem(
                { navController.navigate(RegisterMembership) },
                icon = R.drawable.baseline_person_add_24,
                title = "Register member",
                contentDescription = "register member",
                modifier = modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.medium)))
            QuickAccessItem(
                { navController.navigate(RegisterSession) },
                R.drawable.baseline_more_time_24,
                "Register session",
                "register session",
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
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
fun DashboardScreenPreview() {
    GymusAppTheme {
        DashboardScreen(navController = rememberNavController())
    }
}