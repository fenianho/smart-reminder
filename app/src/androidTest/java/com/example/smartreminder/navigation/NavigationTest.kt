package com.example.smartreminder.navigation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.smartreminder.presentation.MainActivity
import com.example.smartreminder.presentation.navigation.Screen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `app starts on reminder list screen`() {
        // Verify that the app starts on the reminder list screen
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()
    }

    @Test
    fun `can navigate to add reminder screen from reminder list`() {
        // Click on add reminder button
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()

        // Verify we're on the add reminder screen
        composeTestRule.onNodeWithText("添加提醒").assertIsDisplayed()
    }

    @Test
    fun `can navigate back from add reminder screen`() {
        // Navigate to add reminder screen
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()
        composeTestRule.onNodeWithText("添加提醒").assertIsDisplayed()

        // Click back button
        composeTestRule.onNodeWithContentDescription("返回").performClick()

        // Verify we're back on reminder list screen
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()
    }

    @Test
    fun `can navigate to AI settings screen`() {
        // Assuming there's a way to navigate to AI settings (e.g., from a menu or settings button)
        // This test assumes there's a button with content description "AI设置"
        composeTestRule.onNodeWithContentDescription("AI设置").performClick()

        // Verify we're on AI settings screen
        composeTestRule.onNodeWithText("AI设置").assertIsDisplayed()
    }

    @Test
    fun `navigation maintains back stack correctly`() {
        // Start on reminder list
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()

        // Go to add reminder
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()
        composeTestRule.onNodeWithText("添加提醒").assertIsDisplayed()

        // Go back to reminder list
        composeTestRule.onNodeWithContentDescription("返回").performClick()
        composeTestRule.onNodeWithText("提醒列表").assertIsDisplayed()

        // Verify we can still navigate to add reminder again
        composeTestRule.onNodeWithContentDescription("添加提醒").performClick()
        composeTestRule.onNodeWithText("添加提醒").assertIsDisplayed()
    }
}