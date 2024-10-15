package com.example.spike.presentation.ui.vet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.navigation.Destination

@Composable
fun VetNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.VetDestination.VetAppointments.route
    ) {
//        composable(Destination.VetDestination.VetAppointments.route) {
//            VetAppointmentsScreen(navController)
//        }
    }
}