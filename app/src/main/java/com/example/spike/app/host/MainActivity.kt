package com.example.spike.app.host

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

//Pantallas
import com.example.spike.data.network.model.User
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.navigation.GeneralNavHost
import com.example.spike.presentation.ui.shared.screenLogin.LoginViewModel
import com.example.spike.presentation.ui.theme.SpikeTheme
import com.example.spike.utils.enums.SharedPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpikeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Configurar el NavController
                    val navController = rememberNavController()
                    val sharedPreferences = getSharedPreferences(SharedPreferences.USER_PREFS.value, Context.MODE_PRIVATE)
                    val token = sharedPreferences.getString(SharedPreferences.TOKEN.value, null)
                    val role = sharedPreferences.getString(SharedPreferences.ROLE.value, null)

                    GeneralNavHost(navController = navController)
                    if (token != null) {
                        when (role) {
                            "PET_OWNER" -> {
                                navController.navigate(Destination.UserDestination.VetList.route) {
                                    popUpTo(Destination.Login.route) { inclusive = true }
                                }
                            }

                            "VETERINARY_OWNER" -> {
                                navController.navigate(Destination.VetDestination.PrincipalVetScreen.route) {
                                    popUpTo(Destination.Login.route) { inclusive = true }
                                }
                            }

                            "ADMIN" -> {
                                navController.navigate(Destination.AdminDestination.AdminDashboard.route) {
                                    popUpTo(Destination.Login.route) { inclusive = true }
                                }
                            }
                        }

                    } else {
                        navController.navigate(Destination.Login.route)
                    }


                }
            }
        }
    }
}


