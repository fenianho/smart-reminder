package com.example.smartreminder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.smartreminder.presentation.ui.AddReminderScreen
import com.example.smartreminder.presentation.ui.AiSettingsScreen
import com.example.smartreminder.presentation.ui.ReminderDetailScreen
import com.example.smartreminder.presentation.ui.ReminderListScreen

@Composable
fun SmartReminderNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.ReminderList.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.ReminderList.route) {
            ReminderListScreen(
                onAddReminder = { navController.navigate(Screen.AddReminder.route) },
                onReminderClick = { reminder ->
                    navController.navigate(Screen.ReminderDetail.createRoute(reminder.id))
                },
                onSettingsClick = { navController.navigate(Screen.AiSettings.route) }
            )
        }

        composable(Screen.AddReminder.route) {
            AddReminderScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.ReminderDetail.route,
            arguments = listOf(
                navArgument("reminderId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val reminderId = backStackEntry.arguments?.getLong("reminderId") ?: 0L
            ReminderDetailScreen(
                reminderId = reminderId,
                onNavigateBack = { navController.popBackStack() },
                onEditReminder = { reminder ->
                    // TODO: Navigate to edit screen when implemented
                },
                onDeleteReminder = { navController.popBackStack() }
            )
        }

        composable(Screen.AiSettings.route) {
            AiSettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}