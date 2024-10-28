package com.example.spike.presentation.ui.shared.screenRegister.registerUser.registerPet

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowForward

@Composable
fun RegisterScreenDetails(navController: NavController) {
    var petAgeIndex by remember { mutableStateOf(0) }
    var petWeightIndex by remember { mutableStateOf(0) }
    var selectedGender by remember { mutableStateOf("") }
    var selectedSizeIndex by remember { mutableStateOf(0) }

    val ages = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val weightRanges = listOf("Small", "Medium", "Large")
    val genders = listOf("Male", "Female")
    val sizes = listOf("Small", "Medium", "Large", "Giant")

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

            // Scroll horizontal para la edad
            Text(text = "Age (years)", color = Color.White, modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón de flecha izquierda para la edad
                IconButton(onClick = {
                    if (petAgeIndex > 0) petAgeIndex--
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Decrease Age",
                        tint = Color.White
                    )
                }

                LazyRow(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(ages) { age ->
                        Button(
                            onClick = { petAgeIndex = ages.indexOf(age) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (petAgeIndex == ages.indexOf(age)) Color.Gray else Color(0xFF2224A5)
                            )
                        ) {
                            Text(text = age.toString(), color = Color.White)
                        }
                    }
                }

                // Botón de flecha derecha para la edad
                IconButton(onClick = {
                    if (petAgeIndex < ages.lastIndex) petAgeIndex++
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Increase Age",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scroll horizontal para el peso
            Text(text = "Weight", color = Color.White, modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón de flecha izquierda para el peso
                IconButton(onClick = {
                    if (petWeightIndex > 0) petWeightIndex--
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Decrease Weight",
                        tint = Color.White
                    )
                }

                LazyRow(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(weightRanges) { weightRange ->
                        Button(
                            onClick = { petWeightIndex = weightRanges.indexOf(weightRange) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (petWeightIndex == weightRanges.indexOf(weightRange)) Color.Gray else Color(0xFF2224A5)
                            )
                        ) {
                            Text(text = weightRange, color = Color.White)
                        }
                    }
                }

                // Botón de flecha derecha para el peso
                IconButton(onClick = {
                    if (petWeightIndex < weightRanges.lastIndex) petWeightIndex++
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Increase Weight",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Selección de género
            Text(text = "Gender", color = Color.White, modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                genders.forEach { gender ->
                    Button(
                        onClick = { selectedGender = gender },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedGender == gender) Color.Gray else Color(0xFF2224A5)
                        )
                    ) {
                        Text(text = gender, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Selección de tamaño
            Text(text = "Size", color = Color.White, modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botón de flecha izquierda para el tamaño
                IconButton(onClick = {
                    if (selectedSizeIndex > 0) selectedSizeIndex--
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Decrease Size",
                        tint = Color.White
                    )
                }

                LazyRow(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(sizes) { size ->
                        Button(
                            onClick = { selectedSizeIndex = sizes.indexOf(size) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (selectedSizeIndex == sizes.indexOf(size)) Color.Gray else Color(0xFF2224A5)
                            )
                        ) {
                            Text(text = size, color = Color.White)
                        }
                    }
                }

                // Botón de flecha derecha para el tamaño
                IconButton(onClick = {
                    if (selectedSizeIndex < sizes.lastIndex) selectedSizeIndex++
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Increase Size",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(208.dp))

            // Botón "Submit"
            Button(
                onClick = {
                    // Lógica para enviar los datos del formulario
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
                Text(text = "Submit")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenDetailsPreview() {
    val navController = rememberNavController()
    RegisterScreenDetails(navController = navController)
}
