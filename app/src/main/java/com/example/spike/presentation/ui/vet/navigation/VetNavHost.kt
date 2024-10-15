package com.example.spike.presentation.ui.vet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.vet.PrincipalVetScreen

@Composable
fun VetNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.VetDestination.PrincipalVetScreen.route
    ) {
        composable(Destination.VetDestination.PrincipalVetScreen.route) {
            PrincipalVetScreen(navController)
        }
    }
}