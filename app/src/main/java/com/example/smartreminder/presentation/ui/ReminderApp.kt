package com.example.smartreminder.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun ReminderApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "reminder_list"
    ) {
        composable("reminder_list") {
            ReminderListScreen(
                onAddReminder = { 
                    navController.navigate("add_reminder")
                },
                onReminderClick = { reminder ->
                    navController.navigate("reminder_detail/${reminder.id}")
                },
                onSettingsClick = {
                    navController.navigate("ai_settings")
                }
            )
        }
        
        composable("add_reminder") {
            AddReminderScreen(
                onNavigateBack = {
                    navController.navigate("reminder_list") {
                        popUpTo("reminder_list") { inclusive = false }
                    }
                }
            )
        }
        
        composable("reminder_detail/{reminderId}") { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getString("reminderId")?.toLongOrNull() ?: 0L
            ReminderDetailScreen(
                reminderId = reminderId,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onEditReminder = { reminder ->
                    navController.navigate("edit_reminder/${reminder.id}")
                }
            )
        }
        
        composable(
            "edit_reminder/{reminderId}",
            arguments = listOf(navArgument("reminderId") { type = NavType.LongType })
        ) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getLong("reminderId") ?: 0L
            EditReminderScreen(
                reminderId = reminderId,
                onNavigateBack = {
                    navController.navigate("reminder_list") {
                        popUpTo("reminder_list") { inclusive = false }
                    }
                }
            )
        }
        
        composable("ai_settings") {
            AiSettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}