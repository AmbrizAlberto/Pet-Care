package com.example.spike.presentation.ui.shared.screenRegister

import android.icu.number.Scale
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.spike.R



@Composable
fun ReviewScreen(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    // Acceder a los datos del ViewModel
    val role = viewModel.role.value ?: "N/A"
    val veterinarieName = viewModel.veterinarieName.value ?: "N/A"
    val email = viewModel.email.value ?: "N/A"
    val phone = viewModel.phone.value ?: "N/A"
    val street = viewModel.street.value ?: "N/A"
    val locality = viewModel.locality.value ?: "N/A"
    val cologne = viewModel.cologne.value ?: "N/A"
    val number_int = viewModel.number_int.value ?: "N/A"
    val cp = viewModel.cp.value ?: "N/A"
    val rfc = viewModel.rfc.value ?: "N/A"
    val city = viewModel.city.value ?: "N/A"
    val category = viewModel.category.value ?: "N/A"
    val imgUri = viewModel.img.value // URI de la imagen subida

    val context = LocalContext.current // Contexto para el Toast

    // Diseñar la pantalla de revisión
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Review Your Information",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Mostrar imagen subida
            imgUri?.let {
                Image(
                    painter = rememberImagePainter(data = it),
                    contentDescription = "Vet Profile Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(16.dp),
                    contentScale = ContentScale.Crop
                )
            } ?: Text(
                text = "No image selected",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )

            // Mostrar los datos del veterinario
            val infoList = listOf(
                "Role: $role",
                "Name (Vet): $veterinarieName",
                "Email: $email",
                "Phone: $phone",
                "Street: $street",
                "Locality: $locality",
                "Cologne: $cologne",
                "Interior Number: $number_int",
                "Postal Code: $cp",
                "RFC: $rfc",
                "City: $city",
                "Category: $category"
            )

            infoList.forEach { info ->
                Text(text = info, color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón para confirmar y continuar con la solicitud
            Button(
                onClick = {
                    // Llamar al método de registro en el ViewModel
                    viewModel.registerVet()
                    Toast.makeText(context, "Processing registration...", Toast.LENGTH_SHORT).show()
                    Log.d("BotondeEnvio", "Datos enviados: " +
                            "Nombre: ${viewModel.veterinarieName.value}, " +
                            "Email: ${viewModel.email.value}, " +
                            "Teléfono: ${viewModel.phone.value}, " +
                            "Calle: ${viewModel.street.value}, " +
                            "RFC: ${viewModel.rfc.value}, " +
                            "CP: ${viewModel.cp.value}, " )
                    Log.d("ViewModelCompleto", viewModel.toString()) // Asegúrate de tener un método toString() en el ViewModel
                    // Navegar a la pantalla de login
                    navController.navigate("login")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2224A5)
                )
            ) {
                Text(text = "Next", color = Color.White)
            }
        }
    }
}

