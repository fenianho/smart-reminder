package com.example.smartreminder.domain.usecase

import com.example.smartreminder.data.ai.AiServiceManager
import com.example.smartreminder.domain.model.AiParseResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class ParseReminderTextUseCaseTest {

    @Mock
    private lateinit var aiServiceManager: AiServiceManager

    private lateinit var useCase: ParseReminderTextUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = ParseReminderTextUseCase(aiServiceManager)
    }

    @Test
    fun `invoke should return success when AI service parses successfully`() = runTest {
        // Given
        val inputText = "明天下午3点开会"
        val expectedResult = AiParseResult(
            title = "开会",
            description = "会议提醒",
            dateTime = LocalDateTime.of(2024, 1, 2, 15, 0)
        )
        `when`(aiServiceManager.parseReminderText(inputText)).thenReturn(Result.success(expectedResult))

        // When
        val result = useCase(inputText)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedResult, result.getOrNull())
        verify(aiServiceManager).parseReminderText(inputText)
    }

    @Test
    fun `invoke should return failure for blank text`() = runTest {
        // Given
        val blankText = "   "

        // When
        val result = useCase(blankText)

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalArgumentException)
        assertEquals("提醒文本不能为空", result.exceptionOrNull()?.message)
        verify(aiServiceManager, never()).parseReminderText(any())
    }

    @Test
    fun `invoke should return failure when AI service throws exception`() = runTest {
        // Given
        val inputText = "明天开会"
        val exception = RuntimeException("AI service error")
        `when`(aiServiceManager.parseReminderText(inputText)).thenReturn(Result.failure(exception))

        // When
        val result = useCase(inputText)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(aiServiceManager).parseReminderText(inputText)
    }

    @Test
    fun `invoke should trim input text before processing`() = runTest {
        // Given
        val inputText = "  明天下午3点开会  "
        val trimmedText = "明天下午3点开会"
        val expectedResult = AiParseResult(
            title = "开会",
            description = "会议提醒",
            dateTime = LocalDateTime.of(2024, 1, 2, 15, 0)
        )
        `when`(aiServiceManager.parseReminderText(trimmedText)).thenReturn(Result.success(expectedResult))

        // When
        val result = useCase(inputText)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedResult, result.getOrNull())
        verify(aiServiceManager).parseReminderText(trimmedText)
    }
}