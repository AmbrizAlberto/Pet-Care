package com.example.spike.presentation.ui.shared.screenRegister.registerUser

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.navigation.Destination

@Composable
fun RegisterScreenUserDetails(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var cp by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("Select City") }
    var expanded by remember { mutableStateOf(false) }
    val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Miami")

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
                text = "Enter your additional details",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontSize = 25.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TextField de Email
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email@email.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )


            Spacer(modifier = Modifier.height(16.dp))

            // TextField para número telefónico
            TextField(
                value = phone,
                onValueChange = { newValue ->
                    if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                        phone = newValue
                    }
                },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                visualTransformation = VisualTransformation.None,
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Menú desplegable para la ciudad
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
                    .clickable { expanded = true }
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = selectedCity,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown icon",
                        tint = Color.Gray
                    )
                }
            }

            // Menú desplegable que aparece debajo del campo seleccionado
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(text = city) },
                        onClick = {
                            selectedCity = city // Actualiza la ciudad seleccionada
                            expanded = false // Cierra el menú
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TextField para CP
            TextField(
                value = cp,
                onValueChange = { newValue ->
                    if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                        cp = newValue
                    }
                },
                label = { Text("CP") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )
            Spacer(modifier = Modifier.height(288.dp))

            // Botón "Next"
            Button(
                onClick = {
                  //  navController.navigate(Destination.PassRegister.createRoute(userType = "User")) // o "Veterinary" según corresponda
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
fun RegisterScreenUserDetailsPreview() {
    val navController = rememberNavController()
    RegisterScreenUserDetails(navController = navController)
}
