package com.example.spike.presentation.ui.shared.screenRegister.registerVet

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.remember
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.spike.R
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.shared.screenRegister.RegisterViewModel

@Composable
fun RegisterScreenVet(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    var expandedCategories by remember { mutableStateOf(false) }
    val category = listOf("CARE", "NUTRITION", "RECREATION")
    var selectedCategory by remember { mutableStateOf("Select Specialty") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.updateSelectedImageUri(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748)),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                IconButton(
                    onClick = { navController.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                LinearProgressIndicator(
                    progress = 0.5f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = Color(0xFF2224A5)
                )

                Text(
                    text = "Enter your personal information",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier
                        .height(100.dp)
                        .width(120.dp)
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(80.dp)
                        ),
                    contentPadding = PaddingValues(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    viewModel.img.value?.let { uri ->
                        Image(
                            painter = rememberImagePainter(uri),
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } ?: run {
                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = "Upload Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = viewModel.veterinarieName.value,
                        onValueChange = { newValue ->
                            viewModel.veterinarieName.value = newValue
                        },
                        label = { Text("Company name") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    )

                    TextField(
                        value = viewModel.email.value,
                        onValueChange = { viewModel.email.value = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextField(
                            value = viewModel.horaInicio.value,
                            onValueChange = { newValue ->
                                viewModel.horaInicio.value = newValue
                            },
                            label = { Text("Opening Time") },
                            modifier = Modifier.weight(1f).padding(end = 8.dp),
                            shape = MaterialTheme.shapes.medium
                        )

                        TextField(
                            value = viewModel.horaFin.value,
                            onValueChange = { newValue ->
                                viewModel.horaFin.value = newValue
                            },
                            label = { Text("Closing Time") },
                            modifier = Modifier.weight(1f).padding(start = 8.dp),
                            shape = MaterialTheme.shapes.medium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Select Opening Days", color = Color.White, fontSize = 16.sp)

                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DayCheckbox(day = "Monday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                            DayCheckbox(day = "Tuesday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DayCheckbox(day = "Wednesday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                            DayCheckbox(day = "Thursday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DayCheckbox(day = "Friday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                            DayCheckbox(day = "Saturday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            DayCheckbox(day = "Sunday", diasSemana = viewModel.diasSemana, viewModel = viewModel)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)) {
                    Button(
                        onClick = { expandedCategories = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2224A5)
                        )
                    ) {
                        Text(text = selectedCategory)
                    }

                    DropdownMenu(
                        expanded = expandedCategories,
                        onDismissRequest = { expandedCategories = false }
                    ) {
                        category.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    viewModel.category.value = category
                                    expandedCategories = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Botón "Next" que navega a la siguiente pantalla
                Button(
                    onClick = {
                        // Aquí recolectas todos los datos y navegas
                        navController.navigate("register_vet_address")
                        Log.d("Vet", viewModel.toString()) // Asegúrate de tener un método toString() en el ViewModel
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
}

@Composable
fun DayCheckbox(day: String, diasSemana: List<String>, viewModel: RegisterViewModel) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = diasSemana.contains(day),
            onCheckedChange = { isChecked ->
                viewModel.updateSelectedDay(day, isChecked)
            },
            colors = CheckboxDefaults.colors(checkmarkColor = Color.White)
        )
        Text(text = day, color = Color.White)
    }
}



        @Preview
@Composable
fun RegisterScreenVetPreview() {
    val navController = rememberNavController()
    RegisterScreenVet(navController = navController)
}
