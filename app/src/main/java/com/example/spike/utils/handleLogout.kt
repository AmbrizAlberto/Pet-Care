package com.example.spike.utils

import android.content.Context
import androidx.navigation.NavHostController
import com.example.spike.presentation.navigation.Destination

fun handleLogout(navController: NavHostController, context: Context) {
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        remove("user_token")
        apply()
    }

    navController.navigate(Destination.Login.route) {
        popUpTo(Destination.Login.route) { inclusive }
    }
}