package com.example.wishlistapp

import AddEditDetailView
import HomeView
import WishViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(), // ViewModel is provided by default to all screens
    navController: NavHostController = rememberNavController() // Navigation controller to manage navigation
) {
    // Define the navigation host with the start destination as the Home screen
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        // Composable for the Home screen
        composable(Screen.HomeScreen.route) {
            HomeView(navController, viewModel)
        }

        // Composable for the Add/Edit screen, taking an optional ID as a parameter
        composable(
            Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType // Argument type is Long
                    defaultValue = 0L // Default value is 0L, meaning it's a new wish
                    nullable = false // Argument cannot be null
                }
            )
        ) { entry ->
            val id = entry.arguments?.getLong("id") ?: 0L // Retrieve the ID from the arguments
            AddEditDetailView(id = id, viewModel = viewModel, navController = navController)
        }
    }
}
