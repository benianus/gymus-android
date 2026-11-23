package com.example.gymusAndroid.membership.presentation.Memberships

import androidx.paging.PagingData
import com.example.gymusAndroid.membership.domain.models.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MembershipsScreenState(
    val members: Flow<PagingData<Member>> = emptyFlow(),
    val isLoading: Boolean = false,
    val error: String? = null
)
