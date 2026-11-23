package com.example.gymusAndroid.membership.data.dataSource.remote

import com.example.core.dto.ApiResponse
import com.example.core.dto.Error
import com.example.core.dto.ErrorResponseDto
import com.example.core.dto.PagedResponse
import com.example.core.helpers.BASE_URL
import com.example.core.helpers.apiResponse
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberRegisterRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.requests.MemberUpdateRequestDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberCardResponseDto
import com.example.gymusAndroid.membership.data.dataSource.remote.dtos.responses.MemberResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import java.io.File

class MembershipApiServiceImpl(val httpClient: HttpClient) :
    MembershipApiService {
    override suspend fun findAllMembers(
        page: Int,
        pageSize: Int
    ): ApiResponse<PagedResponse<List<MemberResponseDto>>, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.get("$BASE_URL/members?page=$page&pageSize=$pageSize") {
                        contentType(ContentType.Application.Json)
                    }
            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

    override suspend fun registerMember(
        memberRegisterRequestDto: MemberRegisterRequestDto
    ): ApiResponse<Unit, ErrorResponseDto> {
        return try {
            val response = httpClient.post("$BASE_URL/members") {
                contentType(ContentType.MultiPart.FormData)
                setBody(
                    MultiPartFormDataContent(
                        parts = formData {
                            append(
                                "first_name",
                                memberRegisterRequestDto.firstName
                            )
                            append(
                                "last_name",
                                memberRegisterRequestDto.lastName
                            )
                            append("email", memberRegisterRequestDto.email)
                            append(
                                "phone_number",
                                memberRegisterRequestDto.phoneNumber
                            )
                            append("address", memberRegisterRequestDto.address)
                            append(
                                "membership_type",
                                memberRegisterRequestDto.membershipType
                            )
                            append(
                                "medical_certificate",
                                File("").readBytes(),
                                headers {
                                    append(
                                        HttpHeaders.ContentType,
                                        "image/png"
                                    )
                                }
                            )
                            append(
                                "birth_certificate",
                                File("").readBytes(),
                                headers {
                                    append(
                                        HttpHeaders.ContentType,
                                        "image/png"
                                    )
                                }
                            )
                        }
                    )
                )
            }
            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

    override suspend fun recordAttendance(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.post("$BASE_URL/members/record/${memberId}/attendance")
            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

    override suspend fun renewMembership(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        return try {
            val response =
                    httpClient.post("$BASE_URL/members/${memberId}/renew")
            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

    override suspend fun findMemberCard(
        memberId: Int
    ): ApiResponse<MemberCardResponseDto, ErrorResponseDto> {
        return try {
            val response = httpClient.get("$BASE_URL/members/$memberId")
            apiResponse<MemberCardResponseDto, ErrorResponseDto>(
                response
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

    override suspend fun updateMember(
        memberId: Int,
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): ApiResponse<Unit, ErrorResponseDto> {
        return try {
            val response = httpClient.put("$BASE_URL/members/$memberId") {
                setBody(memberUpdateRequestDto)
            }
            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

    override suspend fun deleteMember(memberId: Int): ApiResponse<Unit, ErrorResponseDto> {
        return try {
            val response = httpClient.delete("$BASE_URL/members/$memberId")
            apiResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Error<Nothing, ErrorResponseDto>(
                ErrorResponseDto(
                    e.message ?: "unknow error!"
                )
            )
        }
    }

}