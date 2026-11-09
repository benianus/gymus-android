package com.example.gymusAndroid.core.helpers

import com.example.gymusAndroid.core.dto.ApiResponse
import com.example.gymusAndroid.core.dto.ApiResponse.Error
import com.example.gymusAndroid.core.dto.ApiResponse.Success
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified D, reified E> apiResponse(
    response: HttpResponse
): ApiResponse<D, E> =
        when (response.status.value) {
            in 0..299 -> Success(data = response.body<Success<D, Nothing>>().data)
            else -> Error(error = response.body<Error<Nothing, E>>().error)
        }