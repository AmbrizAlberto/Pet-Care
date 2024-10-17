package com.example.spike.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.ui.shared.screenLogin.LoginScreen
import com.example.spike.presentation.ui.shared.screenRegister.ConfirmationScreen
import com.example.spike.presentation.ui.shared.screenRegister.RegisterScreen
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.RegisterScreenUser
import com.example.spike.presentation.ui.shared.screenRegister.registerVet.RegisterScreenVet
import com.example.spike.presentation.ui.user.appointmentsScreen.AppointmentsScreen
import com.example.spike.presentation.ui.user.mainScreen.PetCareCatalogueScreen
import com.example.spike.presentation.ui.user.profileScreen.ProfileScreen
import com.example.spike.presentation.ui.vet.PrincipalVetScreen

@Composable
fun GeneralNavHost(navController: NavHostController) {
    val selectedItemIndexMenu = remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = Destination.Login.route //
    ) {
//        Shared
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

//        User
        composable(Destination.UserDestination.VetList.route) {
            PetCareCatalogueScreen(selectedItemIndexMenu, navController)
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

//        Vet
        composable(Destination.VetDestination.PrincipalVetScreen.route) {
            PrincipalVetScreen(navController)
        }

    }
}
