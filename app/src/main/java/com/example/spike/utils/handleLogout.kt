package com.example.spike.utils

import android.content.Context
import androidx.navigation.NavHostController
import coil.imageLoader
import com.example.spike.presentation.navigation.Destination
import com.example.spike.utils.enums.SharedPreferences

fun handleLogout(navController: NavHostController, context: Context) {
    val sharedPreferences = context.getSharedPreferences(SharedPreferences.USER_PREFS.value, Context.MODE_PRIVATE)
    val imageLoader = context.imageLoader

    imageLoader.diskCache?.clear()
    imageLoader.memoryCache?.clear()

    with(sharedPreferences.edit()) {
        remove("user_token")
        apply()
    }

    navController.navigate(Destination.Login.route) {
        popUpTo(Destination.Login.route) { inclusive = true }
    }
}