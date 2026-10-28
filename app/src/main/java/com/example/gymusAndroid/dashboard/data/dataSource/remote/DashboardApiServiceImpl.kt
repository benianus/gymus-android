package com.example.gymusAndroid.dashboard.data.dataSource.remote

import com.example.gymusAndroid.core.BASE_URL
import com.example.gymusAndroid.core.dto.ApiResponse
import com.jetbrains.gymusserverjava.memberships.dtos.responses.RecentMemberChecksInDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

class DashboardApiServiceImpl(
    private val httpClient: HttpClient
) : DashboardApiService {
    override suspend fun getActiveMembers(): ApiResponse<Int> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/reports/total-active-memberships-sales") {
                        contentType(ContentType.Application.Json)
                    }

            return if (response.status == HttpStatusCode.OK) {
                response.body()
            } else {
                response.body()
            }
        } catch (e: Exception) {
            ApiResponse(
                data = null,
                errors = listOf(e.message.toString()),
                status = "error"
            )
        }
    }

    override suspend fun getTodaysRevenue(): ApiResponse<Double> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/reports/today-revenue") {
                        contentType(ContentType.Application.Json)
                    }

            return if (response.status == HttpStatusCode.OK) {
                response.body()
            } else {
                response.body()
            }
        } catch (e: Exception) {
            ApiResponse(
                data = null,
                errors = listOf(e.message.toString()),
                status = "error"
            )
        }
    }

    override suspend fun getRecentChecksInMembers(): ApiResponse<Set<RecentMemberChecksInDto>> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/members/recent-checks") {
                        contentType(ContentType.Application.Json)
                        bearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiZW5pYW51cyIsImlhdCI6MTc5Mjk2MzY3NywiZXhwIjoxNzkzMDUwMDc3LCJ1c2VyX2lkIjoxLCJyb2xlIjoiT1dORVIifQ.tLDbE5djuvO8nFhkVIwI_9up5dwtsPgYiH22LRd_GWU")
                    }

            return if (response.status == HttpStatusCode.OK) {
                response.body()
            } else {
                response.body()
            }
        } catch (e: Exception) {
            ApiResponse(
                data = null,
                errors = listOf(e.message.toString()),
                status = "error"
            )
        }
    }
}

