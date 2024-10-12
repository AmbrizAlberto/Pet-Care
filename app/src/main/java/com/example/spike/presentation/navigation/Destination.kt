package com.example.spike.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(
    val route: String,
//    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object VetList: Destination("home")
    data object Profile: Destination("profile")
    data object Login: Destination("login")
    data object Register: Destination("register")
    data object UserRegister: Destination("user_register")
    data object Confirmation: Destination("confirmation")
    data object VetRegister: Destination("vet_register")

}