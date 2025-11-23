package com.example.smartreminder.domain.usecase

import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.repository.ReminderRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class CreateReminderUseCaseTest {

    @Mock
    private lateinit var repository: ReminderRepository

    private lateinit var useCase: CreateReminderUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = CreateReminderUseCase(repository)
    }

    @Test
    fun `invoke should return success when repository inserts successfully`() = runTest {
        // Given
        val reminder = Reminder(
            id = 0,
            title = "Test Reminder",
            description = "Test Description",
            dateTime = LocalDateTime.now(),
            repeatType = com.example.smartreminder.domain.model.RepeatType.NONE,
            isActive = true
        )
        val expectedId = 1L
        `when`(repository.insertReminder(reminder)).thenReturn(expectedId)

        // When
        val result = useCase(reminder)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedId, result.getOrNull())
        verify(repository).insertReminder(reminder)
    }

    @Test
    fun `invoke should return failure when repository throws exception`() = runTest {
        // Given
        val reminder = Reminder(
            id = 0,
            title = "Test Reminder",
            description = "Test Description",
            dateTime = LocalDateTime.now(),
            repeatType = com.example.smartreminder.domain.model.RepeatType.NONE,
            isActive = true
        )
        val exception = RuntimeException("Database error")
        `when`(repository.insertReminder(reminder)).thenThrow(exception)

        // When
        val result = useCase(reminder)

        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(repository).insertReminder(reminder)
    }
}