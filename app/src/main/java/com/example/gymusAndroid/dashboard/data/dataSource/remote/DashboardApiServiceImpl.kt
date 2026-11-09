package com.example.gymusAndroid.dashboard.data.dataSource.remote

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ApiResponse.Error
import com.example.gymusAndroid.core.dto.ApiResponse.Success
import com.example.gymusAndroid.core.dto.ErrorResponseDto
import com.example.gymusAndroid.core.helpers.BASE_URL
import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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

            when (response.status.value) {
                in 0 until 300 -> Success(response.body<Success<Int, Nothing>>().data)
                else -> Error(response.body<Error<Nothing, ErrorResponseDto>>().error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error(ErrorResponseDto(e.message ?: "unknown error"))
        }
    }

    override suspend fun getTodaysRevenue(): ApiResponse<Double, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/reports/today-revenue") {
                        contentType(ContentType.Application.Json)
                    }

            when (response.status.value) {
                in 0 until 300 -> Success(response.body<Success<Double, Nothing>>().data)
                else -> Error(response.body<Error<Nothing, ErrorResponseDto>>().error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error(ErrorResponseDto(e.message ?: "unknown error"))
        }
    }

    override suspend fun getRecentChecksInMembers(): ApiResponse<Set<RecentMemberChecksInDto>, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/members/recent-checks") {
                        contentType(ContentType.Application.Json)
                    }

            when (response.status.value) {
                in 0 until 300 -> Success(response.body<Success<Set<RecentMemberChecksInDto>, Nothing>>().data)
                else -> Error(response.body<Error<Nothing, ErrorResponseDto>>().error)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Error(ErrorResponseDto(e.message ?: "unknown error!"))
        }
    }
}

