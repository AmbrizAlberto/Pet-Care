package com.example.spike.presentation.ui.shared.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.shared.screenLogin.LoginScreen
import com.example.spike.presentation.ui.shared.screenRegister.ConfirmationScreen
import com.example.spike.presentation.ui.shared.screenRegister.RegisterScreen
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.RegisterScreenUser
import com.example.spike.presentation.ui.shared.screenRegister.registerVet.RegisterScreenVet

@Composable
fun SharedNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Login.route //
    ) {
        composable(Destination.Login.route) {
            LoginScreen(navController)
        }
        composable(Destination.Register.route) {
            RegisterScreen(navController)
        }
        composable(Destination.Confirmation.route) {
            ConfirmationScreen(navController)
        }
        composable(Destination.UserRegister.route) {
            RegisterScreenUser(navController)
        }
        composable(Destination.VetRegister.route) {
            RegisterScreenVet(navController)
        }
    }
}
