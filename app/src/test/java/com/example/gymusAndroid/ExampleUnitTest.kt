package com.example.gymusAndroid

import com.example.gymusAndroid.auth.data.dataSource.remote.dto.request.LoginRequestDto
import com.example.gymusAndroid.core.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun shouldReturnOk_whenLoginCredentialsAreCorrect() = runTest {
        // given
        val loginRequestDto = LoginRequestDto("benianus", "123456789")
        val mockEngine = MockEngine { request ->
            respond(
                content = ByteReadChannel(
                    """
                    {
                        "data": {
                            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiZW5pYW51cyIsImlhdCI6MTc4NjY0NjkzMywiZXhwIjoxNzg2NzMzMzMzLCJyb2xlIjoiUk9MRV9PV05FUiJ9.rUE2s1iiIgCL2CdCLTh2thlMQFgHciPj5XFmMUb46bk",
                            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiZW5pYW51cyIsImlhdCI6MTc4NjY0NjkzMywiZXhwIjoxNzg3MjUxNzMzLCJyb2xlIjoiUk9MRV9PV05FUiJ9.YDIKRiyNqoEEtSjIKot0eTXBJsjPcjmu0B3PJkazgfo",
                            "username": "benianus",
                            "role": "ROLE_OWNER",
                            "expiresIn": 1786733333000
                        },
                        "errors": null,
                        "status": "success"
                    }
                """.trimIndent()
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        // when
        val client = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys
                })
            }
        }

        val response = client.post("${BASE_URL}/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequestDto)
        }

        // then
        assertEquals(HttpStatusCode.OK, response.status)
    }
}