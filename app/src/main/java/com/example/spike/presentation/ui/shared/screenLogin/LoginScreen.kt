package com.example.spike.presentation.ui.shared.screenLogin

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spike.R
import com.example.spike.presentation.navigation.Destination


@Composable
fun FiveSidedShape() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val path = Path().apply {
            // Definir el polígono con bordes redondeados
            moveTo(size.width / 2, size.height * 0.25f) // Bajar la punta superior
            lineTo(0f, size.height * 0.5f) // Punto izquierdo superior
            lineTo(0f, size.height) // Punto inferior izquierdo
            lineTo(size.width, size.height) // Punto inferior derecho
            lineTo(size.width, size.height * 0.5f) // Punto derecho superior

            // Redondear la punta superior
            lineTo(
                size.width / 2 + 25.dp.toPx(),
                size.height * 0.25f + 25.dp.toPx()
            ) // Ajustar para la esquina redondeada
            lineTo(
                size.width / 2 - 25.dp.toPx(),
                size.height * 0.25f + 25.dp.toPx()
            ) // Ajustar para la esquina redondeada

            close() // Cerrar la figura
        }

        // Dibujar el polígono
        drawPath(
            path = path,
            color = Color(0xFF4C526A)
        )
    }
}

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(),
    selectedItemIndexMenu: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val loginState = loginViewModel.loginState.value
    val isLoading = loginViewModel.isLoading.value
    val errorMessage = loginViewModel.errorMessage.value

    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF3E4357))
    ) {
        FiveSidedShape()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Logo y texto "Spike"
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 8.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Spike",
                    color = Color.White,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.catbox),
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Fit
                )
            }

            // Campo de correo
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de contraseña
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp),
                shape = MaterialTheme.shapes.medium
            )

            // Casilla "See Password"
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
                Spacer(modifier = Modifier.weight(1f))
                // Botón "Forgot password?"
                Text(
                    text = "Forgot password?",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(44.dp))

            // Mostrar mensaje de error si existe
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Botón "Login"
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        loginViewModel.login(username, password, context)
                    } else {
                        loginViewModel.errorMessage.value = "Please fill in all fields."
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2274A5)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Login", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Navegación a pantalla principal
            LaunchedEffect(loginState) {

                if (loginState != null && loginState.token != null) {
                    when (loginState.user.role) {
                        "PET_OWNER" -> {
                            selectedItemIndexMenu.value = 0
                            navController.navigate(Destination.UserDestination.VetList.route) {
                                popUpTo(Destination.Login.route) { inclusive = true }
                            }
                        }

                        "VETERINARY_OWNER" -> {
                            selectedItemIndexMenu.value = 0
                            navController.navigate(Destination.VetDestination.PrincipalVetScreen.route) {
                                popUpTo(Destination.Login.route) { inclusive = true }
                            }
                        }

                        "ADMIN" -> {
                            navController.navigate(Destination.AdminDestination.AdminDashboard.route) {
                                popUpTo(Destination.Login.route) { inclusive = true }
                            }
                        }

                        else -> {
                            Log.d("LoginDebug", "Unrecognized role: ${loginState.user.role}")
                            loginViewModel.errorMessage.value =
                                "Login failed: Unrecognized user type."
                        }
                    }
                } else if (loginState != null && loginState.token == null) {
                    // Mensaje de error si no existe token
                    loginViewModel.errorMessage.value = "Login failed: Invalid credentials."
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(color = Color.LightGray, modifier = Modifier.weight(1f))
                Text(
                    text = "You don’t have an account?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Divider(color = Color.LightGray, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Botón "Register"
            Button(
                onClick = { navController.navigate(Destination.Register.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Register", fontSize = 18.sp, color = Color(0xFF3E4357))
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    val selectedItemIndexMenu: MutableState<Int> = remember { mutableStateOf(0) }

    LoginScreen(navController = navController, selectedItemIndexMenu = selectedItemIndexMenu)
}






