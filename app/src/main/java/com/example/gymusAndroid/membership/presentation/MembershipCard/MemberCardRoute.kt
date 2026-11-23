package com.example.gymusAndroid.membership.presentation.MembershipCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.example.core.components.BottomNavBar
import com.example.core.components.ErrorMessageDialog
import com.example.core.components.ItemNotFoundErrorMessage
import com.example.core.components.TopBar
import com.example.core.helpers.BASE_URL
import com.example.gymusAndroid.R
import com.example.gymusAndroid.auth.presentation.login.LoginViewModel
import com.example.gymusAndroid.membership.domain.models.MemberCard
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MemberCardRoute(
    widthSizeClass: WindowWidthSizeClass,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    memberCardViewModel: MemberCardViewModel = koinViewModel(),
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val uiState by memberCardViewModel.uiState.collectAsState()

    ErrorMessageDialog(
        error = uiState.error,
        onDismissRequest = memberCardViewModel::onErrorChanged,
        onConfirmation = memberCardViewModel::onErrorChanged
    )

    when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            if (uiState.isLoading) CircularProgressIndicator()
            MemberCardCompactScreen(
                memberCardViewModel = memberCardViewModel,
                onClickLogout = loginViewModel::logout,
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
    memberCardViewModel: MemberCardViewModel,
    onClickLogout: () -> Unit,
    memberCard: MemberCard?,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBar(
                onClickLogout = onClickLogout,
                title = stringResource(R.string.member_card),
                navHostController = navHostController,
                showNavigateUpIcon = true
            )
        },
        bottomBar = {
            BottomNavBar(
                navHostController = navHostController
            )
        }
    ) { innerPadding ->
        if (memberCard != null) {
            MemberCardItems(
                memberCardViewModel = memberCardViewModel,
                modifier = modifier.padding(innerPadding),
                memberCard = memberCard
            )
        } else {
            ItemNotFoundErrorMessage(modifier.padding(innerPadding))
        }
    }
}

@Composable
private fun MemberCardItems(
    memberCardViewModel: MemberCardViewModel,
    modifier: Modifier = Modifier,
    memberCard: MemberCard,
    imageLoader: ImageLoader = koinInject()
) {
    Card(
        modifier = modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium
    ) {
        MemberCardDetails(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = dimensionResource(R.dimen.medium))
                .weight(3f),
            memberCard = memberCard,
            imageLoader = imageLoader
        )
        MemberCardActions(
            memberCardViewModel = memberCardViewModel,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.medium))
                .weight(1f),
            memberCard = memberCard
        )
    }
}

@Composable
private fun MemberCardActions(
    memberCardViewModel: MemberCardViewModel,
    modifier: Modifier = Modifier,
    memberCard: MemberCard
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        MemberCardActionItem(
            memberId = memberCard.memberId,
            text = stringResource(R.string.check_in),
            onClick = memberCardViewModel::recordNewAttendance,
            enabled = !memberCard.isChecked,
        )
        MemberCardActionItem(
            memberId = memberCard.memberId,
            text = stringResource(R.string.renew_membership),
            onClick = memberCardViewModel::renewMembership,
            enabled = !memberCard.isActive,
        )
        MemberCardActionItem(
            memberId = memberCard.memberId,
            text = stringResource(R.string.delete_member),
            onClick = memberCardViewModel::deleteMembership,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            )
        )
    }
}

@Composable
fun MemberCardActionItem(
    memberId: Int,
    text: String,
    onClick: (memberId: Int) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    Button(
        onClick = { onClick(memberId) },
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        colors = colors,
        enabled = enabled
    ) { Text(text = text) }
}

@Composable
private fun MemberCardDetails(
    modifier: Modifier = Modifier,
    memberCard: MemberCard,
    imageLoader: ImageLoader
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.medium),
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            model = "$BASE_URL/files/download/${memberCard.personalPhoto}",
            contentDescription = stringResource(R.string.profile_photo),
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader,
            placeholder = painterResource(R.drawable.baseline_data_thresholding_24),
            error = painterResource(R.drawable.baseline_error_24)
        )
        MemberCardDetailsItem(
            text = stringResource(
                R.string.full_name,
                memberCard.firstName,
                memberCard.lastName
            ),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
        )
        MemberCardDetailsItem(
            text = stringResource(
                R.string.birthdate,
                memberCard.birthDate
            )
        )
        MemberCardDetailsItem(
            text = stringResource(
                R.string.join_date,
                memberCard.joinDate
            )
        )
        MemberCardDetailsItem(
            text = stringResource(
                R.string.membership_end_date,
                memberCard.endDate
            )
        )
        MemberCardDetailsItem(
            text = stringResource(
                R.string.last_check_in,
                memberCard.checkDate ?: "none"
            )
        )
        MemberCardDetailsItem(
            text = stringResource(
                R.string.last_renewal,
                memberCard.lastRenewal ?: "none"
            )
        )
    }
}

@Composable
private fun MemberCardDetailsItem(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        style = style,
        fontWeight = fontWeight
    )
}

@Composable
fun MemberCardMediumScreen(modifier: Modifier = Modifier) {
    Text("Medium Screen")
}

@Composable
fun MemberCardExpandedScreen(modifier: Modifier = Modifier) {
    Text("ExpandedScreen")
}