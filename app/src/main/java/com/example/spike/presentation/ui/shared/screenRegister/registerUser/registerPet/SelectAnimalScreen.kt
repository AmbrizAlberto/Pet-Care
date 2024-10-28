package com.example.spike.presentation.ui.shared.screenRegister.registerUser.registerPet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.spike.R
@Composable
fun AnimalSelectionScreen(navController: NavController) {
    var selectedAnimal by remember { mutableStateOf("") }

    // Lista de animales y sus respectivas imágenes
    val animalList = listOf(
        "Cat" to R.drawable.catbox,
        "Dog" to R.drawable.catbox,
        "Rabbit" to R.drawable.catbox,
        "Bird" to R.drawable.catbox,
        "Reptile" to R.drawable.catbox,
        "Other" to R.drawable.catbox
    )

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
                text = "Select the animal as your pet",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Opciones de animales
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(animalList) { (animal, imageRes) ->
                    AnimalButton(
                        animal = animal,
                        imageRes = imageRes,
                        isSelected = selectedAnimal == animal,
                        onClick = { selectedAnimal = animal }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Para empujar el botón hacia abajo

            // Botón Next
            Button(
                onClick = {
                    if (selectedAnimal.isNotEmpty()) {
                        navController.navigate("register_pet")
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
                    .height(48.dp)
                    .width(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2224A5) // Color del botón
                )
            ) {
                Text(
                    text = "Next",
                    color = Color.White // Color del texto del botón
                )
            }
        }
    }
}

@Composable
fun AnimalButton(animal: String, imageRes: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .border(
                width = 2.dp,
                color = if (isSelected) Color.Blue else Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))    ) {
        // Imagen correspondiente al animal
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = animal,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp),
            contentScale = ContentScale.Fit
        )

        // Texto del animal
        Text(
            text = animal,
            color = if (isSelected) Color.Blue else Color.White,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalSelectionScreenPreview() {
    val navController = rememberNavController()
    AnimalSelectionScreen(navController = navController)
}

