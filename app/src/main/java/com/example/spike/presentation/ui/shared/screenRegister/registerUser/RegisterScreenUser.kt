package com.example.spike.presentation.ui.shared.screenRegister.registerUser

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.spike.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenUser(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
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
                    .padding(16.dp),
                color = Color(0xFF2224A5)
            )

            // Título
            Text(
                text = "Enter your personal information",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(64.dp))

                // Botón para subir imagen
                Button(
                    onClick = {
                        // Acción para seleccionar imagen
                    },
                    modifier = Modifier
                        .height(120.dp)
                        .width(150.dp)
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(80.dp)
                        ),
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    // Imagen dentro del botón
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "Upload Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentScale = ContentScale.Fit
                    )
                }

                Text(
                    text = "Upload Image",
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Fila para First Name y Last Name
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // TextField de First Name
                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    shape = MaterialTheme.shapes.medium
                )

                // TextField de Last Name
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    shape = MaterialTheme.shapes.medium
                )
            }

            Spacer(modifier = Modifier.height(248.dp))

            // Botón "Next"
            Button(
                onClick = {
                    navController.navigate("register_user_details")
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
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

@Preview
@Composable
fun RegisterScreenUserPreview() {
    val navController = rememberNavController()
    RegisterScreenUser(navController = navController)
}

