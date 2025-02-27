package com.example.bistroftchallenge.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bistroftchallenge.core.navigation.ScreenRoutes
import com.example.bistroftchallenge.presentation.home.HomeScreen
import com.example.bistroftchallenge.presentation.main.WelcomeScreen


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.HomeScreen.route, //TODO():Change it to WelcomeScreen later
        enterTransition = {
            slideInHorizontally(tween(700)) { 1090 }
        },
        exitTransition = {
            slideOutHorizontally(tween(700)) { 1090 }
        },
        popEnterTransition = {
            slideInHorizontally(tween(700)) { 1090 }
        },
        popExitTransition = {
            slideOutHorizontally(tween(700)) { 1090 }
        },
    ) {
        //Main
        composable(route = ScreenRoutes.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        //Home
        composable(route = ScreenRoutes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
    }
}