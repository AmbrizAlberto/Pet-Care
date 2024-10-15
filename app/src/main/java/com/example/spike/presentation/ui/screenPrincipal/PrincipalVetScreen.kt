package com.example.spike.presentation.ui.screenPrincipal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PrincipalVetScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hola, veterinaria",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrincipalVetScreenPreview() {
    val navController = rememberNavController()
    PrincipalVetScreen(navController = navController)
}
