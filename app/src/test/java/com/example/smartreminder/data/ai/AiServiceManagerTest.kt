package com.example.smartreminder.data.ai

import com.example.smartreminder.domain.model.AiConfig
import com.example.smartreminder.domain.model.AiParseResult
import com.example.smartreminder.domain.model.AiProvider
import com.example.smartreminder.domain.model.AiTimeSuggestion
import com.example.smartreminder.domain.repository.AiConfigRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class AiServiceManagerTest {

    @Mock
    private lateinit var aiConfigRepository: AiConfigRepository

    @Mock
    private lateinit var aiServiceFactory: AiServiceFactory

    @Mock
    private lateinit var nlpParser: NlpParser

    @Mock
    private lateinit var aiService: AiService

    private lateinit var aiServiceManager: AiServiceManager

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        aiServiceManager = AiServiceManager(aiConfigRepository, aiServiceFactory, nlpParser)
    }

    @Test
    fun `initialize should return true when AI config is available and service is created successfully`() = runTest {
        // Given
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "test-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenReturn(aiService)

        // When
        val result = aiServiceManager.initialize()

        // Then
        assertTrue(result)
        verify(aiConfigRepository).getActiveAiConfig()
        verify(aiServiceFactory).createAiService(config)
    }

    @Test
    fun `initialize should return false when no active AI config is found`() = runTest {
        // Given
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(null))

        // When
        val result = aiServiceManager.initialize()

        // Then
        assertFalse(result)
    }

    @Test
    fun `initialize should return false when service creation fails`() = runTest {
        // Given
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "test-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenThrow(RuntimeException("Creation failed"))

        // When
        val result = aiServiceManager.initialize()

        // Then
        assertFalse(result)
    }

    @Test
    fun `parseReminderText should use AI service when available and successful`() = runTest {
        // Given
        val text = "明天下午3点开会"
        val expectedResult = AiParseResult(
            title = "开会",
            description = "会议提醒",
            dateTime = LocalDateTime.of(2024, 1, 2, 15, 0)
        )
        `when`(aiService.isAvailable()).thenReturn(true)
        `when`(aiService.parseReminderText(text)).thenReturn(Result.success(expectedResult))

        // Set up the service
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "test-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenReturn(aiService)
        aiServiceManager.initialize()

        // When
        val result = aiServiceManager.parseReminderText(text)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedResult, result.getOrNull())
        verify(aiService).parseReminderText(text)
        verify(nlpParser, never()).parseReminderText(any())
    }

    @Test
    fun `parseReminderText should fallback to NLP when AI service is not available`() = runTest {
        // Given
        val text = "明天下午3点开会"
        val expectedResult = AiParseResult(
            title = "开会",
            description = "会议提醒",
            dateTime = LocalDateTime.of(2024, 1, 2, 15, 0)
        )
        `when`(nlpParser.parseReminderText(text)).thenReturn(expectedResult)

        // When
        val result = aiServiceManager.parseReminderText(text)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedResult, result.getOrNull())
        verify(nlpParser).parseReminderText(text)
    }

    @Test
    fun `parseReminderText should fallback to NLP when AI service fails with API key error`() = runTest {
        // Given
        val text = "明天下午3点开会"
        val aiException = AiException.InvalidApiKeyException("Invalid API key")
        val nlpResult = AiParseResult(
            title = "开会",
            description = "会议提醒",
            dateTime = LocalDateTime.of(2024, 1, 2, 15, 0)
        )

        // Set up AI service
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "invalid-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenReturn(aiService)
        aiServiceManager.initialize()

        `when`(aiService.isAvailable()).thenReturn(true)
        `when`(aiService.parseReminderText(text)).thenReturn(Result.failure(aiException))
        `when`(nlpParser.parseReminderText(text)).thenReturn(nlpResult)

        // When
        val result = aiServiceManager.parseReminderText(text)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(nlpResult, result.getOrNull())
        verify(aiService).parseReminderText(text)
        verify(nlpParser).parseReminderText(text)
    }

    @Test
    fun `parseReminderText should return failure when both AI and NLP fail`() = runTest {
        // Given
        val text = "明天下午3点开会"
        val aiException = AiException.NetworkException("Network error")

        // Set up AI service
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "test-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenReturn(aiService)
        aiServiceManager.initialize()

        `when`(aiService.isAvailable()).thenReturn(true)
        `when`(aiService.parseReminderText(text)).thenReturn(Result.failure(aiException))
        `when`(nlpParser.parseReminderText(text)).thenThrow(RuntimeException("NLP failed"))

        // When
        val result = aiServiceManager.parseReminderText(text)

        // Then
        assertTrue(result.isFailure)
        assertEquals(aiException, result.exceptionOrNull())
    }

    @Test
    fun `suggestReminderTime should use AI service when available and successful`() = runTest {
        // Given
        val title = "开会"
        val description = "重要会议"
        val expectedSuggestions = listOf(
            AiTimeSuggestion(
                dateTime = LocalDateTime.of(2024, 1, 2, 14, 0),
                confidence = 0.9f,
                reason = "工作时间"
            )
        )

        // Set up AI service
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "test-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenReturn(aiService)
        aiServiceManager.initialize()

        `when`(aiService.isAvailable()).thenReturn(true)
        `when`(aiService.suggestReminderTime(title, description)).thenReturn(Result.success(expectedSuggestions))

        // When
        val result = aiServiceManager.suggestReminderTime(title, description)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedSuggestions, result.getOrNull())
        verify(aiService).suggestReminderTime(title, description)
        verify(nlpParser, never()).suggestReminderTime(any(), any())
    }

    @Test
    fun `isAiServiceAvailable should return true when AI service is available`() = runTest {
        // Given
        val config = AiConfig(
            id = "test-id",
            provider = AiProvider.OPENAI,
            apiKey = "test-key",
            isEnabled = true
        )
        `when`(aiConfigRepository.getActiveAiConfig()).thenReturn(flowOf(config))
        `when`(aiServiceFactory.createAiService(config)).thenReturn(aiService)
        `when`(aiService.isAvailable()).thenReturn(true)
        aiServiceManager.initialize()

        // When
        val result = aiServiceManager.isAiServiceAvailable()

        // Then
        assertTrue(result)
        verify(aiService).isAvailable()
    }

    @Test
    fun `isAiServiceAvailable should return false when no AI service is initialized`() = runTest {
        // When
        val result = aiServiceManager.isAiServiceAvailable()

        // Then
        assertFalse(result)
    }
}