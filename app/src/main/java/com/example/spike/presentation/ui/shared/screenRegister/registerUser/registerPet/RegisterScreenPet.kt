package com.example.spike.presentation.ui.shared.screenRegister.registerUser.registerPet

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

import com.example.spike.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPetScreen(navController: NavController) {
    var petName by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748))
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Cambiado para que los elementos se alineen en la parte superior
        ) {
            // Flecha de retroceso
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Barra de progreso
            LinearProgressIndicator(
                progress = 0.5f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = Color(0xFF2224A5)
            )

            // Título
            Text(
                text = "Register Your Pet",
                fontSize = 25.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Botón para subir imagen
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(80.dp))
                    .border(
                        width = 2.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(80.dp)
                    )
                    .clickable {
                        // Acción para seleccionar imagen
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUri),
                        contentDescription = "Pet Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(80.dp)),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.upload_image),
                        contentDescription = "Upload Icon",
                        modifier = Modifier.size(60.dp),
                        tint = Color.White
                    )
                }
            }

            Text(
                text = "Upload Image",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "What is the name of your pet?",
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Campo de texto para el nombre de la mascota
            TextField(
                value = petName,
                onValueChange = { petName = it },
                label = { Text("Pet Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
            )

            // Espaciado para separar los campos del botón
            Spacer(modifier = Modifier.height(220.dp))

            // Botón "Next"
            Button(
                onClick = {
                    navController.navigate("register_pet_details")
                },
                modifier = Modifier
                    .align(Alignment.End) // Mantiene el botón a la derecha
                    .padding(vertical = 16.dp)
                    .height(48.dp)
                    .width(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2224A5)
                )
            ) {
                Text(text = "Next")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun RegisterAnimalScreenPreview() {
    val navController = rememberNavController()
    RegisterPetScreen(navController = navController)
}
