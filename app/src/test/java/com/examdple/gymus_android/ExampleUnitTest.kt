package com.examdple.gymus_android

import com.examdple.gymus_android.auth.data.dataSource.remote.dto.request.LoginRequestDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
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
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun shouldReturnOk_whenLoginCredentialsAreCorrect() = runTest {
        // given
        val loginRequestDto = LoginRequestDto("benianus", "123456789")

        // when
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys
                })
            }
        }
        
        val response = client.post("http://10.0.2.2:5138/api/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequestDto)
        }

        // then
        assertEquals(HttpStatusCode.OK, response.status)
    }
}