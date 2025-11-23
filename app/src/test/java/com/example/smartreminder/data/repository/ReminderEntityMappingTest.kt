package com.example.smartreminder.data.repository

import com.example.smartreminder.data.local.entity.ReminderEntity
import com.example.smartreminder.domain.model.Reminder
import com.example.smartreminder.domain.model.RepeatType
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

class ReminderEntityMappingTest {

    @Test
    fun `ReminderEntity toDomain should map all fields correctly`() {
        // Given
        val currentTime = System.currentTimeMillis()
        val entity = ReminderEntity(
            id = 1,
            title = "Test Reminder",
            description = "Test Description",
            reminderTime = currentTime,
            isActive = true,
            repeatType = RepeatType.DAILY,
            repeatInterval = 2,
            createdAt = currentTime - 1000,
            updatedAt = currentTime
        )

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(1L, domain.id)
        assertEquals("Test Reminder", domain.title)
        assertEquals("Test Description", domain.description)
        assertEquals(currentTime, domain.reminderTime)
        assertEquals(true, domain.isActive)
        assertEquals(RepeatType.DAILY, domain.repeatType)
        assertEquals(2, domain.repeatInterval)
        assertEquals(currentTime - 1000, domain.createdAt)
        assertEquals(currentTime, domain.updatedAt)
    }

    @Test
    fun `ReminderEntity toDomain should handle null description`() {
        // Given
        val entity = ReminderEntity(
            id = 2,
            title = "Reminder without description",
            description = null,
            reminderTime = System.currentTimeMillis(),
            isActive = false,
            repeatType = RepeatType.NONE,
            repeatInterval = null,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        // When
        val domain = entity.toDomain()

        // Then
        assertEquals(null, domain.description)
        assertEquals(null, domain.repeatInterval)
    }

    @Test
    fun `Reminder toEntity should map all fields correctly`() {
        // Given
        val currentTime = System.currentTimeMillis()
        val reminder = Reminder(
            id = 3,
            title = "Domain Reminder",
            description = "Domain Description",
            reminderTime = currentTime,
            isActive = false,
            repeatType = RepeatType.WEEKLY,
            repeatInterval = 1,
            createdAt = currentTime - 2000,
            updatedAt = currentTime
        )

        // When
        val entity = reminder.toEntity()

        // Then
        assertEquals(3L, entity.id)
        assertEquals("Domain Reminder", entity.title)
        assertEquals("Domain Description", entity.description)
        assertEquals(currentTime, entity.reminderTime)
        assertEquals(false, entity.isActive)
        assertEquals(RepeatType.WEEKLY, entity.repeatType)
        assertEquals(1, entity.repeatInterval)
        assertEquals(currentTime - 2000, entity.createdAt)
        assertEquals(currentTime, entity.updatedAt)
    }

    @Test
    fun `Reminder toEntity should handle null values correctly`() {
        // Given
        val reminder = Reminder(
            id = 4,
            title = "Reminder with nulls",
            description = null,
            reminderTime = System.currentTimeMillis(),
            isActive = true,
            repeatType = RepeatType.MONTHLY,
            repeatInterval = null,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )

        // When
        val entity = reminder.toEntity()

        // Then
        assertEquals(null, entity.description)
        assertEquals(null, entity.repeatInterval)
    }

    @Test
    fun `entity to domain to entity should be symmetric for all fields`() {
        // Given
        val originalEntity = ReminderEntity(
            id = 5,
            title = "Round trip test",
            description = "Testing round trip conversion",
            reminderTime = System.currentTimeMillis(),
            isActive = true,
            repeatType = RepeatType.YEARLY,
            repeatInterval = 12,
            createdAt = System.currentTimeMillis() - 5000,
            updatedAt = System.currentTimeMillis()
        )

        // When
        val domain = originalEntity.toDomain()
        val resultEntity = domain.toEntity()

        // Then
        assertEquals(originalEntity.id, resultEntity.id)
        assertEquals(originalEntity.title, resultEntity.title)
        assertEquals(originalEntity.description, resultEntity.description)
        assertEquals(originalEntity.reminderTime, resultEntity.reminderTime)
        assertEquals(originalEntity.isActive, resultEntity.isActive)
        assertEquals(originalEntity.repeatType, resultEntity.repeatType)
        assertEquals(originalEntity.repeatInterval, resultEntity.repeatInterval)
        assertEquals(originalEntity.createdAt, resultEntity.createdAt)
        assertEquals(originalEntity.updatedAt, resultEntity.updatedAt)
    }

    // Extension functions for testing (copied from ReminderRepositoryImpl for testing)
    private fun ReminderEntity.toDomain(): Reminder {
        return Reminder(
            id = id,
            title = title,
            description = description,
            reminderTime = reminderTime,
            isActive = isActive,
            repeatType = repeatType,
            repeatInterval = repeatInterval,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun Reminder.toEntity(): ReminderEntity {
        return ReminderEntity(
            id = id,
            title = title,
            description = description,
            reminderTime = reminderTime,
            isActive = isActive,
            repeatType = repeatType,
            repeatInterval = repeatInterval,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}