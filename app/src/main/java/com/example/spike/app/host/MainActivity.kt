package com.example.spike.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.theme.SpikeTheme
import androidx.compose.ui.Modifier

//Pantallas
import com.example.spike.presentation.ui.screenLogin.LoginScreen
import com.example.spike.presentation.ui.screenPrincipal.PrincipalPetOwnerScreen
import com.example.spike.presentation.ui.screenPrincipal.PrincipalVetScreen
import com.example.spike.presentation.ui.screenRegister.ConfirmationScreen
import com.example.spike.presentation.ui.screenRegister.RegisterScreen
import com.example.spike.presentation.ui.screenRegister.registerUser.RegisterScreenUser
import com.example.spike.presentation.ui.screenRegister.registerVet.RegisterScreenVet


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpikeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Configurar el NavController
                    val navController = rememberNavController()
                    // Configurar la NavHost
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen(navController = navController, modifier = Modifier.fillMaxSize())
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }
                        composable("user_register") {
                            RegisterScreenUser(navController = navController)
                        }
                        composable("confirmation") {
                            ConfirmationScreen(navController = navController)
                        }
                        composable("vet_register") {
                            RegisterScreenVet(navController = navController)
                        }
                        composable("principalPetOwner") {
                            PrincipalPetOwnerScreen(navController = navController)
                        }
                        composable("principalVet") {
                            PrincipalVetScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


