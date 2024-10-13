package com.example.spike.presentation.ui.user.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.user.profileScreen.ProfileScreen
import com.example.spike.presentation.ui.user.appointmentsScreen.AppointmentsScreen
import com.example.spike.presentation.ui.user.mainScreen.PetCareCatalogueScreen

@Composable
fun UserNavHost(navController: NavHostController) {
    val selectedItemIndexMenu = remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = Destination.UserDestination.VetList.route
    ) {
        composable(Destination.UserDestination.VetList.route) {
            PetCareCatalogueScreen(
                navController = navController,
                selectedItemIndexMenu = selectedItemIndexMenu
            )
        }

        composable(Destination.UserDestination.Profile.route) {
            ProfileScreen(
                navController = navController,
                selectedItemIndexMenu = selectedItemIndexMenu
            )
        }

        composable(Destination.UserDestination.AppointmentsScreen.route) {
            AppointmentsScreen(navController = navController)
        }
    }
}