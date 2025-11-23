package com.example.smartreminder.data.ai

import com.example.smartreminder.domain.model.AiParseResult
import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class NlpParserTest {

    private val nlpParser = NlpParser()

    @Test
    fun `parseReminderText should extract title and time for tomorrow morning meeting`() {
        // Given
        val input = "明天下午3点开会"

        // When
        val result = nlpParser.parseReminderText(input)

        // Then
        assertEquals("开会", result.title)
        assertNull(result.description)
        assertNotNull(result.scheduledTime)
        // Note: The exact timestamp will vary, but we can verify it's set
    }

    @Test
    fun `parseReminderText should handle text without time information`() {
        // Given
        val input = "买牛奶"

        // When
        val result = nlpParser.parseReminderText(input)

        // Then
        assertEquals("买牛奶", result.title)
        assertNull(result.description)
        assertNull(result.scheduledTime)
    }

    @Test
    fun `parseReminderText should extract title and description with multiple sentences`() {
        // Given
        val input = "明天上午9点参加项目会议。记得带上笔记本电脑和项目资料。"

        // When
        val result = nlpParser.parseReminderText(input)

        // Then
        assertEquals("参加项目会议", result.title)
        assertEquals("记得带上笔记本电脑和项目资料", result.description)
        assertNotNull(result.scheduledTime)
    }

    @Test
    fun `parseReminderText should remove time keywords from title`() {
        // Given
        val input = "提醒我明天上午10点去银行办理业务"

        // When
        val result = nlpParser.parseReminderText(input)

        // Then
        assertEquals("去银行办理业务", result.title)
        assertNull(result.description)
        assertNotNull(result.scheduledTime)
    }

    @Test
    fun `parseReminderText should handle empty input gracefully`() {
        // Given
        val input = ""

        // When
        val result = nlpParser.parseReminderText(input)

        // Then
        assertEquals("", result.title)
        assertNull(result.description)
        assertNull(result.scheduledTime)
    }

    @Test
    fun `parseReminderText should limit title length to 50 characters`() {
        // Given
        val longTitle = "这是一个非常非常长的标题，超过了五十个字符的限制，希望能够被正确截断"
        val input = "$longTitle，明天上午9点"

        // When
        val result = nlpParser.parseReminderText(input)

        // Then
        assertTrue(result.title.length <= 50)
        assertTrue(result.title.startsWith("这是一个非常非常长的标题"))
    }

    @Test
    fun `suggestReminderTime should provide appropriate suggestions for work meeting`() {
        // Given
        val title = "项目会议"
        val description = "讨论新功能开发"

        // When
        val suggestions = nlpParser.suggestReminderTime(title, description)

        // Then
        assertTrue(suggestions.isNotEmpty())
        suggestions.forEach { suggestion ->
            assertNotNull(suggestion.suggestedTime)
            assertTrue(suggestion.suggestedTime > System.currentTimeMillis())
            assertNotNull(suggestion.reason)
        }
    }

    @Test
    fun `suggestReminderTime should provide appropriate suggestions for exercise`() {
        // Given
        val title = "晨跑"
        val description = "跑步锻炼身体"

        // When
        val suggestions = nlpParser.suggestReminderTime(title, description)

        // Then
        assertTrue(suggestions.isNotEmpty())
        // Should suggest morning and evening times for exercise
        val hasMorningSuggestion = suggestions.any { it.reason.contains("早晨") }
        val hasEveningSuggestion = suggestions.any { it.reason.contains("傍晚") }
        assertTrue(hasMorningSuggestion || hasEveningSuggestion)
    }

    @Test
    fun `suggestReminderTime should provide default suggestions for unknown task types`() {
        // Given
        val title = "未知任务"
        val description = "没有任何特殊类型的内容"

        // When
        val suggestions = nlpParser.suggestReminderTime(title, description)

        // Then
        assertTrue(suggestions.isNotEmpty())
        // Should include default suggestions like "1小时后", "明天同一时间", "今天下午"
        val hasOneHourLater = suggestions.any { it.reason.contains("1小时") }
        val hasTomorrow = suggestions.any { it.reason.contains("明天") }
        val hasAfternoon = suggestions.any { it.reason.contains("下午") }
        assertTrue(hasOneHourLater || hasTomorrow || hasAfternoon)
    }

    @Test
    fun `suggestReminderTime should filter out past times`() {
        // Given
        val title = "测试过期时间"
        val description = "应该过滤掉过去的时间"

        // When
        val suggestions = nlpParser.suggestReminderTime(title, description)

        // Then
        val currentTime = System.currentTimeMillis()
        suggestions.forEach { suggestion ->
            assertTrue("Suggested time should be in the future: ${suggestion.suggestedTime} > $currentTime",
                suggestion.suggestedTime > currentTime)
        }
    }

    @Test
    fun `suggestReminderTime should handle null description`() {
        // Given
        val title = "只有标题的任务"
        val description = null

        // When
        val suggestions = nlpParser.suggestReminderTime(title, description)

        // Then
        assertTrue(suggestions.isNotEmpty())
        suggestions.forEach { suggestion ->
            assertNotNull(suggestion.suggestedTime)
            assertNotNull(suggestion.reason)
        }
    }

    @Test
    fun `parseReminderText should handle various time formats`() {
        val testCases = listOf(
            "明天上午9点开会" to true,
            "后天下午2点吃饭" to true,
            "下周一上午10点面试" to true,
            "3点半开会" to true,
            "1小时后提醒" to true,
            "30分钟后打电话" to true,
            "买东西" to false,
            "学习英语" to false
        )

        testCases.forEach { (input, shouldHaveTime) ->
            // When
            val result = nlpParser.parseReminderText(input)

            // Then
            if (shouldHaveTime) {
                assertNotNull("Should have time for input: $input", result.scheduledTime)
            } else {
                assertNull("Should not have time for input: $input", result.scheduledTime)
            }
        }
    }
}