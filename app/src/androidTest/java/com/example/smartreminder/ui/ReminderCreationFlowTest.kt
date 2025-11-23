package com.example.smartreminder.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.smartreminder.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ReminderCreationFlowTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `complete reminder creation flow works correctly`() {
        // Start on reminder list screen
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()

        // Navigate to add reminder screen
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()
        composeTestRule.onNodeWithText("添加提醒").assertIsDisplayed()

        // Enter reminder details
        composeTestRule.onNodeWithText("提醒标题").performTextInput("测试会议")
        composeTestRule.onNodeWithText("提醒描述").performTextInput("项目进度讨论会")

        // Select time (assuming there's a time picker)
        composeTestRule.onNodeWithContentDescription("选择时间").performClick()
        // Select tomorrow 10:00 AM (this would need to be adjusted based on actual UI)
        composeTestRule.onNodeWithText("10").performClick() // Hour
        composeTestRule.onNodeWithText("00").performClick() // Minute
        composeTestRule.onNodeWithText("确定").performClick()

        // Save the reminder
        composeTestRule.onNodeWithText("保存").performClick()

        // Verify we're back on the reminder list
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()

        // Verify the reminder appears in the list
        composeTestRule.onNodeWithText("测试会议").assertIsDisplayed()
        composeTestRule.onNodeWithText("项目进度讨论会").assertIsDisplayed()
    }

    @Test
    fun `AI text parsing integration works in add reminder screen`() {
        // Navigate to add reminder screen
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()

        // Find AI parsing input field (assuming there's a text input for natural language)
        composeTestRule.onNodeWithText("输入提醒文字").performTextInput("明天下午3点参加项目会议")

        // Trigger AI parsing (assuming there's a parse button or auto-parsing)
        composeTestRule.onNodeWithText("智能解析").performClick()

        // Verify that fields are auto-filled
        composeTestRule.onNodeWithText("参加项目会议").assertIsDisplayed() // Title should be filled
        // Time should also be set, but we can't easily verify the time picker value
    }

    @Test
    fun `form validation prevents saving invalid reminders`() {
        // Navigate to add reminder screen
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()

        // Try to save without entering title
        composeTestRule.onNodeWithText("保存").performClick()

        // Verify error message is shown
        composeTestRule.onNodeWithText("标题不能为空").assertIsDisplayed()

        // Enter title but no time
        composeTestRule.onNodeWithText("提醒标题").performTextInput("测试提醒")
        composeTestRule.onNodeWithText("保存").performClick()

        // Verify time validation error
        composeTestRule.onNodeWithText("请选择提醒时间").assertIsDisplayed()
    }

    @Test
    fun `reminder detail view shows correct information`() {
        // Assuming there's at least one reminder in the list
        // Click on a reminder in the list
        composeTestRule.onNodeWithText("测试会议").performClick()

        // Verify detail screen shows correct information
        composeTestRule.onNodeWithText("测试会议").assertIsDisplayed()
        composeTestRule.onNodeWithText("项目进度讨论会").assertIsDisplayed()

        // Test navigation back
        composeTestRule.onNodeWithContentDescription("返回").performClick()
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()
    }
}