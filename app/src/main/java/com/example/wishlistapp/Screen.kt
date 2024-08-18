package com.example.wishlistapp

// Sealed class to define the different screens in the app
sealed class Screen(val route: String) {
    // HomeScreen route
    data object HomeScreen : Screen("home_screen")

    // AddScreen route with an optional ID parameter
    data object AddScreen : Screen("add_screen")
}
