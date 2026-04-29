package com.workly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.workly.ui.screens.HomeScreen
import com.workly.ui.screens.LoginScreen
import com.workly.ui.screens.ProfileTypeScreen

object Routes {
    const val LOGIN = "login"
    const val PROFILE_TYPE = "profile_type"
    const val HOME_CLIENT = "home_client"
    const val HOME_WORKER = "home_worker"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Routes.PROFILE_TYPE)
                }
            )
        }

        composable(Routes.PROFILE_TYPE) {
            ProfileTypeScreen(
                onClientSelected = {
                    navController.navigate(Routes.HOME_CLIENT)
                },
                onWorkerSelected = {
                    navController.navigate(Routes.HOME_WORKER)
                }
            )
        }

        composable(Routes.HOME_CLIENT) {
            HomeScreen(userType = "Cliente")
        }

        composable(Routes.HOME_WORKER) {
            HomeScreen(userType = "Profissional")
        }
    }
}