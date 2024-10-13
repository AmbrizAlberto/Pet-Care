package com.example.spike.presentation.ui.admin.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.navigation.Destination

@Composable
fun AdminNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.AdminDestination.Dashboard.route
    ) {
//        composable(Destination.AdminDestination.Dashboard.route) {
//            AdminDashboardScreen(navController)
//        }
//        ...

    }
}