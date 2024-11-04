package com.example.spike.presentation.ui.shared.screenRegister


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ConfirmationScreen(viewModel: RegisterViewModel = hiltViewModel()) {
    val role = viewModel.role.value ?: "PET_OWNER" // Proporcionar un valor por defecto si es nulo

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
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Great, let's start!",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "You are a ${if (role == "VETERINARY_OWNER") "Veterinary Owner" else "Pet Owner"}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/*@Preview
@Composable
fun ConfirmationScreenPreview() {
    // Crear un ViewModel de prueba simulado para el Preview
    val fakeViewModel = object : RegisterViewModel() {
        init {
            role.value = "VETERINARY_OWNER"
        }
    }

    ConfirmationScreen(viewModel = fakeViewModel)
}
*/