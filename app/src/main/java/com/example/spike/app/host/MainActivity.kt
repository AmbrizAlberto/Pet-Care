package com.example.spike.app.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.theme.SpikeTheme
import com.example.spike.presentation.ui.mainScreen.PetCareCatalogueScreen
import com.example.spike.presentation.ui.profileScreen.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            SpikeTheme {
                val navController = rememberNavController()
                val selectedItemIndexMenu = remember { mutableIntStateOf(0) }
                NavHost(
                    navController = navController,
                    startDestination = Destination.VetList.route
                ) {
                    composable(
                        route = Destination.VetList.route
                    ) {
                        PetCareCatalogueScreen(
                            selectedItemIndexMenu = selectedItemIndexMenu,
                            navController = navController
                        )
                    }
                    composable(
                        route = Destination.Profile.route
                    ) {
                        ProfileScreen(
                            navController,
                            selectedItemIndexMenu
                        )
                    }
                }
            }
        }
    }
}