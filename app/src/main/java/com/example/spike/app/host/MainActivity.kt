package com.example.spike.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier

//Pantallas
import com.example.spike.data.model.User
import com.example.spike.presentation.ui.admin.navigation.AdminNavHost
import com.example.spike.presentation.ui.shared.navigation.SharedNavHost
import com.example.spike.presentation.ui.theme.SpikeTheme
import com.example.spike.presentation.ui.user.navigation.UserNavHost
import com.example.spike.presentation.ui.vet.navigation.VetNavHost


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpikeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Configurar el NavController
                    val navController = rememberNavController()
                    val userRole = getUserRole()

                    when (userRole) {
                        "admin" -> AdminNavHost(navController)
                        "user" -> UserNavHost(navController)
                        "vet" -> VetNavHost(navController)
                        else -> SharedNavHost(navController) // Si no está autenticado o no tiene un rol válido
                    }
                }
            }
        }
    }

    private fun getUserRole(): String {
        val exampleUser = User("Test", "user")

        return exampleUser.role
    }
}


