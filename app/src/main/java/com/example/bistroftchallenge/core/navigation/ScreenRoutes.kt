package com.example.bistroftchallenge.core.navigation

sealed class ScreenRoutes(val route: String) {

    //Main
    object WelcomeScreen: ScreenRoutes("welcome_screen")

    //Home
    object HomeScreen: ScreenRoutes("home_screen")
}