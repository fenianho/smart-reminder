package com.example.smartreminder.data.local.converter

import com.example.smartreminder.domain.model.RepeatType
import org.junit.Assert.assertEquals
import org.junit.Test

class RepeatTypeConverterTest {

    private val converter = RepeatTypeConverter()

    @Test
    fun `fromRepeatType should convert RepeatType to string`() {
        // Given
        val repeatType = RepeatType.DAILY

        // When
        val result = converter.fromRepeatType(repeatType)

        // Then
        assertEquals("DAILY", result)
    }

    @Test
    fun `toRepeatType should convert valid string to RepeatType`() {
        // Given
        val value = "WEEKLY"

        // When
        val result = converter.toRepeatType(value)

        // Then
        assertEquals(RepeatType.WEEKLY, result)
    }

    @Test
    fun `toRepeatType should return NONE for invalid string`() {
        // Given
        val invalidValue = "INVALID_TYPE"

        // When
        val result = converter.toRepeatType(invalidValue)

        // Then
        assertEquals(RepeatType.NONE, result)
    }

    @Test
    fun `toRepeatType should handle empty string gracefully`() {
        // Given
        val emptyValue = ""

        // When
        val result = converter.toRepeatType(emptyValue)

        // Then
        assertEquals(RepeatType.NONE, result)
    }

    @Test
    fun `converter should be symmetric for all valid RepeatType values`() {
        // Test all enum values
        RepeatType.values().forEach { repeatType ->
            // When
            val stringValue = converter.fromRepeatType(repeatType)
            val result = converter.toRepeatType(stringValue)

            // Then
            assertEquals(repeatType, result)
        }
    }
}