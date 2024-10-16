package com.example.spike.app.host

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
import com.example.spike.data.model.User
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.navigation.GeneralNavHost
import com.example.spike.presentation.ui.shared.screenLogin.LoginViewModel
import com.example.spike.presentation.ui.theme.SpikeTheme
import kotlin.math.log


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpikeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Configurar el NavController
                    val navController = rememberNavController()
                    val userRole = getUserRole()
//                    val loginViewModel = viewModel<LoginViewModel>()
//                    val loginState = loginViewModel.loginState.value

                    GeneralNavHost(navController)

                }
            }
        }
    }

    private fun getUserRole(): String {
        val exampleUser = User("Test", "notuser")

        return exampleUser.role
    }
}


