package com.example.spike.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(
    val route: String,
//    val arguments: List<NamedNavArgument> = emptyList()
) {
    //    Shared
    data object Login : Destination("login")
    data object Register : Destination("register")
    data object Confirmation : Destination("confirmation")
    data object UserRegister : Destination("user_register")
    data object VetRegister : Destination("vet_register")

    sealed class UserDestination(route: String) : Destination(route) {
        data object VetList : Destination("home")
        data object Profile : Destination("profile")
        data object AppointmentsScreen : Destination("appointments_screen")
    }

    sealed class AdminDestination(route: String) : Destination(route) {
        //        EJEMPLOS
        data object AdminDashboard : Destination("admin_dashboard")
        data object AdminUsers : Destination("admin_users")
        data object AdminConsults : Destination("admin_consults")
        data object AdminClients : Destination("admin_clients")
    }

    sealed class VetDestination(route: String) : Destination(route) {
        data object PrincipalVetScreen : Destination("principal_vet_screen")
    }


}