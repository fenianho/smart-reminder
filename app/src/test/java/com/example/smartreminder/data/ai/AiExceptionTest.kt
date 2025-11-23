package com.example.smartreminder.data.ai

import org.junit.Assert.*
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import org.json.JSONException

class AiExceptionTest {

    @Test
    fun `toAiException should convert HttpException with 401 to InvalidApiKeyException`() {
        // Given
        val response = Response.error<String>(401, okhttp3.ResponseBody.create(null, ""))
        val httpException = HttpException(response)

        // When
        val result = httpException.toAiException()

        // Then
        assertTrue(result is AiException.InvalidApiKeyException)
        assertEquals("API密钥无效", result.message)
    }

    @Test
    fun `toAiException should convert HttpException with 429 to RateLimitException`() {
        // Given
        val response = Response.error<String>(429, okhttp3.ResponseBody.create(null, ""))
        val httpException = HttpException(response)

        // When
        val result = httpException.toAiException()

        // Then
        assertTrue(result is AiException.RateLimitException)
        assertEquals("请求频率过高，请稍后再试", result.message)
    }

    @Test
    fun `toAiException should convert HttpException with 500 to ServiceUnavailableException`() {
        // Given
        val response = Response.error<String>(500, okhttp3.ResponseBody.create(null, ""))
        val httpException = HttpException(response)

        // When
        val result = httpException.toAiException()

        // Then
        assertTrue(result is AiException.ServiceUnavailableException)
        assertTrue(result.message?.contains("AI服务暂时不可用") == true)
    }

    @Test
    fun `toAiException should convert HttpException with other codes to NetworkException`() {
        // Given
        val response = Response.error<String>(404, okhttp3.ResponseBody.create(null, ""))
        val httpException = HttpException(response)

        // When
        val result = httpException.toAiException()

        // Then
        assertTrue(result is AiException.NetworkException)
        assertEquals("HTTP错误: 404", result.message)
    }

    @Test
    fun `toAiException should convert SocketTimeoutException to NetworkException`() {
        // Given
        val socketTimeoutException = SocketTimeoutException("Connection timed out")

        // When
        val result = socketTimeoutException.toAiException()

        // Then
        assertTrue(result is AiException.NetworkException)
        assertEquals("网络连接超时", result.message)
        assertEquals(socketTimeoutException, result.cause)
    }

    @Test
    fun `toAiException should convert UnknownHostException to NetworkException`() {
        // Given
        val unknownHostException = UnknownHostException("Unable to resolve host")

        // When
        val result = unknownHostException.toAiException()

        // Then
        assertTrue(result is AiException.NetworkException)
        assertEquals("网络连接失败，请检查网络设置", result.message)
        assertEquals(unknownHostException, result.cause)
    }

    @Test
    fun `toAiException should convert IOException to NetworkException`() {
        // Given
        val ioException = IOException("Network IO error")

        // When
        val result = ioException.toAiException()

        // Then
        assertTrue(result is AiException.NetworkException)
        assertEquals("网络IO错误", result.message)
        assertEquals(ioException, result.cause)
    }

    @Test
    fun `toAiException should convert JSONException to ParseException`() {
        // Given
        val jsonException = JSONException("Invalid JSON format")

        // When
        val result = jsonException.toAiException()

        // Then
        assertTrue(result is AiException.ParseException)
        assertEquals("响应数据解析失败", result.message)
        assertEquals(jsonException, result.cause)
    }

    @Test
    fun `toAiException should convert unknown exception to UnknownException`() {
        // Given
        val runtimeException = RuntimeException("Some unexpected error")

        // When
        val result = runtimeException.toAiException()

        // Then
        assertTrue(result is AiException.UnknownException)
        assertTrue(result.message?.startsWith("未知错误:") == true)
        assertEquals(runtimeException, result.cause)
    }

    @Test
    fun `AiException subclasses should inherit from AiException base class`() {
        // Test that all exception types are instances of AiException
        val exceptions = listOf(
            AiException.InvalidApiKeyException(),
            AiException.NetworkException("test"),
            AiException.ServiceUnavailableException("test"),
            AiException.RateLimitException("test"),
            AiException.ParseException("test"),
            AiException.ConfigurationException("test"),
            AiException.UnknownException("test")
        )

        exceptions.forEach { exception ->
            assertTrue("Exception should be instance of AiException", exception is AiException)
        }
    }

    @Test
    fun `AiException should support custom messages and causes`() {
        // Given
        val cause = RuntimeException("Original cause")
        val customMessage = "Custom error message"

        // When
        val exception = AiException.NetworkException(customMessage, cause)

        // Then
        assertEquals(customMessage, exception.message)
        assertEquals(cause, exception.cause)
    }
}