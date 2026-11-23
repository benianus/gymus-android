package com.example.gymusAndroid.dashboard.data.dataSource.remote

import com.example.core.dto.ApiResponse
import com.example.core.dto.Error
import com.example.core.dto.ErrorResponseDto
import com.example.core.helpers.BASE_URL
import com.example.core.helpers.apiResponse
import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class DashboardApiServiceImpl(
    private val httpClient: HttpClient
) : DashboardApiService {
    override suspend fun getActiveMembers(): ApiResponse<Int, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/reports/total-active-memberships-sales") {
                        contentType(ContentType.Application.Json)
                    }

            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknown error"
                )
            )
        }
    }

    override suspend fun getTodaysRevenue(): ApiResponse<Double, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/reports/today-revenue") {
                        contentType(ContentType.Application.Json)
                    }

            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknown error"
                )
            )
        }
    }

    override suspend fun getRecentChecksInMembers(): ApiResponse<Set<RecentMemberChecksInDto>, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/members/recent-checks") {
                        contentType(ContentType.Application.Json)
                    }

            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknown error!"
                )
            )
        }
    }
}

