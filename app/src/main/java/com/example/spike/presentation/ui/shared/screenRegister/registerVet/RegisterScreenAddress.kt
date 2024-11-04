package com.example.spike.presentation.ui.shared.screenRegister.registerVet

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.shared.screenRegister.RegisterViewModel

@Composable
fun RegisterScreenVetAddress(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    var expandedCity by remember { mutableStateOf(false) }
    var expandedLocality by remember { mutableStateOf(false) }
    val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Miami")
    val localities = listOf("Locality 1", "Locality 2", "Locality 3")

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

            // TextField de calle
            TextField(
                value = viewModel.street.value,
                onValueChange = { newValue -> viewModel.street.value = newValue },
                label = { Text("Street") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // TextField para el número interior
            TextField(
                value = viewModel.number_int.value,
                onValueChange = { newValue ->
                    viewModel.number_int.value = newValue  // Guardar en el ViewModel
                    Log.d("RegisterViewModel", "Number Int actualizado: ${viewModel.number_int.value}")
                },
                label = { Text("Number Int") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Menú desplegable para la ciudad
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
                    .clickable { expandedCity = true }
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = viewModel.city.value,  // Usar el valor del ViewModel
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

            // Menú desplegable de ciudades
            DropdownMenu(
                expanded = expandedCity,
                onDismissRequest = { expandedCity = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(text = city) },
                        onClick = {
                            viewModel.city.value = city
                            Log.d("RegisterViewModel", "City actualizado: ${viewModel.city.value}")
                            expandedCity = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Menú desplegable para la localidad
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
                    .clickable { expandedLocality = true }
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = viewModel.locality.value,  // Usar el valor del ViewModel
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

            // Menú desplegable de localidades
            DropdownMenu(
                expanded = expandedLocality,
                onDismissRequest = { expandedLocality = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                localities.forEach { locality ->
                    DropdownMenuItem(
                        text = { Text(text = locality) },
                        onClick = {
                            viewModel.locality.value = locality
                            Log.d("RegisterViewModel", "Locality actualizado: ${viewModel.locality.value}")
                            expandedLocality = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TextField para cologne
            TextField(
                value = viewModel.cologne.value,
                onValueChange = { newValue ->
                    viewModel.cologne.value = newValue
                    Log.d("RegisterViewModel", "Cologne actualizado: ${viewModel.cologne.value}")
                },
                label = { Text("Cologne") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Fila para RFC y CP
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // TextField para RFC
                TextField(
                    value = viewModel.rfc.value,
                    onValueChange = { newValue ->
                        viewModel.rfc.value = newValue
                        Log.d("RegisterViewModel", "RFC actualizado: ${viewModel.rfc.value}")
                    },
                    label = { Text("RFC") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 26.dp),
                    shape = MaterialTheme.shapes.medium
                )

                // TextField para CP
                TextField(
                    value = viewModel.cp.value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                            viewModel.cp.value = newValue
                            Log.d("RegisterViewModel", "CP actualizado: ${viewModel.cp.value}")
                        }
                    },
                    label = { Text("CP") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 26.dp),
                    shape = MaterialTheme.shapes.medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TextField para número telefónico
            TextField(
                value = viewModel.phone.value,  // Directamente desde el ViewModel
                onValueChange = { newValue ->
                    if (newValue.length <= 10 && newValue.all { it.isDigit() }) {
                        viewModel.phone.value = newValue  // Actualizar el ViewModel
                        Log.d("RegisterViewModel", "Phone actualizado: ${viewModel.phone.value}")
                    }
                },
                label = { Text("Phone Number") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            Spacer(modifier = Modifier.height(58.dp))

            // Botón "Next"
            Button(
                onClick = {
                    Log.d("Vet", viewModel.toString()) // Asegúrate de tener un método toString() en el ViewModel
                    navController.navigate("register_pass")
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
fun RegisterScreenVetAdressPreview() {
    val navController = rememberNavController()
    RegisterScreenVetAddress(navController = navController)
}