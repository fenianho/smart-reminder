package com.example.smartreminder.data.repository

import com.example.smartreminder.data.local.dao.ReminderDao
import com.example.smartreminder.data.local.entity.ReminderEntity
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.model.RepeatType
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime

class ReminderRepositoryImplTest {

    @Mock
    private lateinit var reminderDao: ReminderDao

    private lateinit var repository: ReminderRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ReminderRepositoryImpl(reminderDao)
    }

    @Test
    fun `getActiveReminders should map entities to domain objects`() = runTest {
        // Given
        val entities = listOf(
            ReminderEntity(
                id = 1,
                title = "Test Reminder",
                description = "Test Description",
                reminderTime = System.currentTimeMillis(),
                isActive = true,
                repeatType = RepeatType.DAILY,
                repeatInterval = 1,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
        )
        `when`(reminderDao.getActiveReminders()).thenReturn(flowOf(entities))

        // When & Then
        repository.getActiveReminders().test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("Test Reminder", result[0].title)
            assertEquals("Test Description", result[0].description)
            assertTrue(result[0].isActive)
            assertEquals(RepeatType.DAILY, result[0].repeatType)
            awaitComplete()
        }
    }

    @Test
    fun `getReminderById should return mapped domain object when entity exists`() = runTest {
        // Given
        val entity = ReminderEntity(
            id = 1,
            title = "Test Reminder",
            description = "Test Description",
            reminderTime = System.currentTimeMillis(),
            isActive = true,
            repeatType = RepeatType.NONE,
            repeatInterval = 0,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        `when`(reminderDao.getReminderById(1)).thenReturn(flowOf(entity))

        // When & Then
        repository.getReminderById(1).test {
            val result = awaitItem()
            assertEquals("Test Reminder", result?.title)
            assertEquals("Test Description", result?.description)
            awaitComplete()
        }
    }

    @Test
    fun `getReminderById should return null when entity does not exist`() = runTest {
        // Given
        `when`(reminderDao.getReminderById(1)).thenReturn(flowOf(null))

        // When & Then
        repository.getReminderById(1).test {
            val result = awaitItem()
            assertEquals(null, result)
            awaitComplete()
        }
    }

    @Test
    fun `insertReminder should convert domain to entity and call dao`() = runTest {
        // Given
        val reminder = Reminder(
            id = 0,
            title = "New Reminder",
            description = "New Description",
            reminderTime = System.currentTimeMillis(),
            isActive = true,
            repeatType = RepeatType.WEEKLY,
            repeatInterval = 1,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        val expectedId = 123L
        `when`(reminderDao.insertReminder(any(ReminderEntity::class.java))).thenReturn(expectedId)

        // When
        val result = repository.insertReminder(reminder)

        // Then
        assertEquals(expectedId, result)
        verify(reminderDao).insertReminder(argThat { entity ->
            entity.title == "New Reminder" &&
            entity.description == "New Description" &&
            entity.repeatType == RepeatType.WEEKLY
        })
    }

    @Test
    fun `updateReminder should convert domain to entity and call dao`() = runTest {
        // Given
        val reminder = Reminder(
            id = 1,
            title = "Updated Reminder",
            description = "Updated Description",
            reminderTime = System.currentTimeMillis(),
            isActive = false,
            repeatType = RepeatType.MONTHLY,
            repeatInterval = 1,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        // When
        repository.updateReminder(reminder)

        // Then
        verify(reminderDao).updateReminder(argThat { entity ->
            entity.id == 1L &&
            entity.title == "Updated Reminder" &&
            entity.isActive == false &&
            entity.repeatType == RepeatType.MONTHLY
        })
    }

    @Test
    fun `deleteReminder should convert domain to entity and call dao`() = runTest {
        // Given
        val reminder = Reminder(
            id = 1,
            title = "Reminder to Delete",
            description = "Description",
            reminderTime = System.currentTimeMillis(),
            isActive = true,
            repeatType = RepeatType.NONE,
            repeatInterval = 0,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        // When
        repository.deleteReminder(reminder)

        // Then
        verify(reminderDao).deleteReminder(argThat { entity ->
            entity.id == 1L &&
            entity.title == "Reminder to Delete"
        })
    }

    @Test
    fun `deactivateReminder should call dao with correct id`() = runTest {
        // Given
        val reminderId = 42L

        // When
        repository.deactivateReminder(reminderId)

        // Then
        verify(reminderDao).deactivateReminder(reminderId)
    }
}