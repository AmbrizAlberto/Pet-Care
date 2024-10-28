package com.example.spike.presentation.ui.shared.screenRegister

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.unit.sp
import com.example.spike.R
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.navigation.Destination
import kotlinx.coroutines.delay


@Composable
fun RegisterScreen(navController: NavController) {
    // Estado de selección para los botones
    var selectedButton by remember { mutableStateOf("") }
    var navigateToNextScreen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF243748),
                        Color(0xFF4B749F)
                    )
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Before you start, which one are you?",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Botón "User"
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            selectedButton = "User"
                            navigateToNextScreen = true
                        },
                        modifier = Modifier
                            .size(150.dp)
                            .border(
                                2.dp,
                                if (selectedButton == "User") Color.Blue else Color.White,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "User") Color(0xFF2274A5) else Color.Transparent
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.user5),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text = "User",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Botón "Veterinary"
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            selectedButton = "Veterinary"
                            navigateToNextScreen = true
                        },
                        modifier = Modifier
                            .size(150.dp)
                            .border(
                                2.dp,
                                if (selectedButton == "Veterinary") Color.Blue else Color.White,
                                shape = RoundedCornerShape(12.dp)
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "Veterinary") Color(0xFF2274A5) else Color.Transparent
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hospital2),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        text = "Veterinary",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }

    // Navegación con retraso
    if (navigateToNextScreen) {
        LaunchedEffect(Unit) {
            when (selectedButton) {
                "User" -> navController.navigate(Destination.UserRegister.route)
                "Veterinary" -> navController.navigate(Destination.VetRegister.route)
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController = navController)
}
