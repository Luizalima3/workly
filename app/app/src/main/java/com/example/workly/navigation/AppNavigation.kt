package com.example.workly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workly.view.ClientHomeScreen
import com.example.workly.view.CreateServiceScreen
import com.example.workly.view.LoginScreen
import com.example.workly.view.ProfileScreen
import com.example.workly.view.ProviderHomeScreen
import com.example.workly.view.SignupScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(navController)
        }

        composable("signup") {
            SignupScreen(navController)
        }

        composable("client_home") {
            ClientHomeScreen(navController)
        }

        composable("provider_home") {
            ProviderHomeScreen(navController)
        }

        composable("create_service") {
            CreateServiceScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }
    }
}