package com.example.spike.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spike.presentation.ui.adminviewsScreen.AdminClientsScreen
import com.example.spike.presentation.ui.adminviewsScreen.AdminConsultsScreen
import com.example.spike.presentation.ui.adminviewsScreen.AdminUsersScreen
import com.example.spike.presentation.ui.dashboardScreen.AdminDashboardScreen
import com.example.spike.presentation.ui.shared.screenLogin.LoginScreen
import com.example.spike.presentation.ui.shared.screenRegister.ConfirmationScreen
import com.example.spike.presentation.ui.shared.screenRegister.RegisterScreen
import com.example.spike.presentation.ui.shared.screenRegister.RegisterScreenPass
import com.example.spike.presentation.ui.shared.screenRegister.RegisterViewModel
import com.example.spike.presentation.ui.shared.screenRegister.ReviewScreen
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.registerPet.AnimalSelectionScreen
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.registerPet.RegisterPetScreen
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.registerPet.RegisterScreenDetails
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.RegisterScreenUser
import com.example.spike.presentation.ui.shared.screenRegister.registerUser.RegisterScreenUserDetails
import com.example.spike.presentation.ui.shared.screenRegister.registerVet.RegisterScreenVet
import com.example.spike.presentation.ui.shared.screenRegister.registerVet.RegisterScreenVetAddress
import com.example.spike.presentation.ui.user.appointmentsScreen.AppointmentsScreen
import com.example.spike.presentation.ui.user.mainScreen.PetCareCatalogueScreen
import com.example.spike.presentation.ui.user.profileScreen.ProfileScreen
import com.example.spike.presentation.ui.vet.PrincipalVetScreen

@Composable
fun GeneralNavHost(navController: NavHostController) {
    val selectedItemIndexMenu = remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = Destination.Login.route
    ) {
//        Shared
        composable(Destination.Login.route) {
            LoginScreen(navController, selectedItemIndexMenu = selectedItemIndexMenu)
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
        composable(Destination.UserDetailsRegister.route) {
            RegisterScreenUserDetails(navController)
        }
        composable(Destination.PassRegister.route) { backStackEntry ->
            RegisterScreenPass(navController)
        }

        composable(Destination.VetRegister.route) { backStackEntry ->
            RegisterScreenVet(navController)
        }
        composable(Destination.VetAddressRegister.route) { backStackEntry ->
            RegisterScreenVetAddress(navController)
        }
       /* composable(Destination.Review.route) { backStackEntry ->
            ReviewScreen(navController)
        }*/
        composable(Destination.AnimalRegister.route) {
            AnimalSelectionScreen(navController)
        }
        composable(Destination.PetRegister.route){
            RegisterPetScreen(navController)
        }
        composable(Destination.PetDetailsRegister.route){
            RegisterScreenDetails(navController)
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

//      Admin
        composable(Destination.AdminDestination.AdminUsers.route) {
            AdminUsersScreen()
        }
        composable(Destination.AdminDestination.AdminConsults.route) {
            AdminConsultsScreen()
        }
        composable(Destination.AdminDestination.AdminClients.route) {
            AdminClientsScreen()
        }
        composable(Destination.AdminDestination.AdminDashboard.route) {
            AdminDashboardScreen(navController = navController)
        }
    }
}
