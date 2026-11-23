package com.example.core.helpers

import com.example.core.dto.ApiResponse
import com.example.core.dto.Error
import com.example.core.dto.Success
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified D, reified E> apiResponse(
    response: HttpResponse
): ApiResponse<D, E> {
    return when (response.status.value) {
        in 0..299 -> Success(data = response.body<Success<D, Nothing>>().data)
        else -> Error(error = response.body<Error<Nothing, E>>().error)
    }
}