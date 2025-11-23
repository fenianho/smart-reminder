package com.example.smartreminder.presentation.navigation

sealed class Screen(val route: String) {
    object ReminderList : Screen("reminder_list")
    object AddReminder : Screen("add_reminder")
    object ReminderDetail : Screen("reminder_detail/{reminderId}") {
        fun createRoute(reminderId: Long) = "reminder_detail/$reminderId"
    }
    object AiSettings : Screen("ai_settings")
}