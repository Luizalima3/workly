package com.workly.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.workly.cliente.ClienteFormScreen
import com.workly.cliente.ClienteListScreen
import com.workly.cliente.ClienteViewModel
import com.workly.ui.screens.HomeScreen
import com.workly.ui.screens.LoginScreen
import com.workly.ui.screens.ProfileTypeScreen

object Routes {
    const val LOGIN = "login"
    const val PROFILE_TYPE = "profile_type"
    const val HOME_CLIENT = "home_client"
    const val HOME_WORKER = "home_worker"
    const val CLIENTE_LIST = "cliente_list"
    const val CLIENTE_FORM = "cliente_form"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val clienteViewModel: ClienteViewModel = viewModel()

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
            HomeScreen(
                userType = "Cliente",
                onVerClientesClick = {
                    navController.navigate(Routes.CLIENTE_LIST)
                }
            )
        }

        composable(Routes.HOME_WORKER) {
            HomeScreen(
                userType = "Prestador"
            )
        }

        composable(Routes.CLIENTE_LIST) {
            ClienteListScreen(
                viewModel = clienteViewModel,
                onNovoCliente = {
                    navController.navigate(Routes.CLIENTE_FORM)
                }
            )
        }

        composable(Routes.CLIENTE_FORM) {
            ClienteFormScreen(
                viewModel = clienteViewModel,
                onSalvar = {
                    navController.popBackStack()
                }
            )
        }
    }
}