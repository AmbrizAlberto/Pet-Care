package com.example.spike.presentation.ui.shared.screenRegister

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Visibility
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.spike.presentation.navigation.Destination

@Composable
fun RegisterScreenPass(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    // Estados para la contraseña y su visibilidad
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Contexto necesario para mostrar Toast
    val context = LocalContext.current

    // Extraemos los valores del ViewModel
    val password = viewModel.password.value

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

            LinearProgressIndicator(
                progress = 0.75f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                color = Color(0xFF2224A5)
            )

            Text(
                text = "Registro para:",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(text = "Create Password", fontSize = 24.sp, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = { viewModel.password.value = it },  // Guardamos la contraseña en el ViewModel
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                Checkbox(
                    checked = passwordVisible,
                    onCheckedChange = { passwordVisible = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF2274A5),
                        uncheckedColor = Color.White,
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = "See Password",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Confirm Password", fontSize = 24.sp, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },  // Mantener confirmPassword en el estado local
                label = { Text("Confirm Password") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
            ) {
                Checkbox(
                    checked = confirmPasswordVisible,
                    onCheckedChange = { confirmPasswordVisible = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF2274A5),
                        uncheckedColor = Color.White,
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = "See Confirm Password",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(300.dp))

            Button(
                onClick = {
                    when {
                        password.isEmpty() || confirmPassword.isEmpty() -> {
                            Toast.makeText(context, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
                        }
                        password != confirmPassword -> {
                            Toast.makeText(context, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show()
                        }
                        !isPasswordValid(password) -> {
                            Toast.makeText(context, "La contraseña debe tener al menos 8 caracteres, incluir una mayúscula, un número y un símbolo especial.", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Log.d("RegisterScreen", "Contraseña enviada: $password")
                            Toast.makeText(context, "Registro exitoso!", Toast.LENGTH_SHORT).show()
                            navController.navigate("review")
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2224A5)
                )
            ) {
                Text("Submit", color = Color.White)
            }
        }
    }
}

// Función para validar la contraseña
fun isPasswordValid(password: String): Boolean {
    // Verifica que la longitud sea suficiente
    if (password.length < 8) return false

    // Verifica que contenga al menos una mayúscula, un dígito y un símbolo especial
    val hasUpperCase = password.any { it.isUpperCase() }
    val hasDigit = password.any { it.isDigit() }
    val hasSpecialChar = password.any { it in "!@#\$%^&*()-_=+{}[]|:;<>,.?/~`" }

    return hasUpperCase && hasDigit && hasSpecialChar
}




@Preview (showBackground  = true)
@Composable
fun RegisterScreenUserPassPreview() {
    val navController = rememberNavController()
    RegisterScreenPass(navController = navController)
}