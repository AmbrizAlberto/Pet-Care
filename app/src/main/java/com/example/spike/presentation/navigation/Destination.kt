package com.example.spike.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(
    val route: String,
//    val arguments: List<NamedNavArgument> = emptyList()
) {
    data object VetList: Destination("home")
    data object Profile: Destination("profile")
}