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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.ui.user.mainScreen.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreenUser(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf("Select City") }
    var expanded by remember { mutableStateOf(false) }
    var privacyAccepted by remember { mutableStateOf(false) }
    var showPrivacyPolicy by remember { mutableStateOf(false) }

    val cities = listOf("New York", "Los Angeles", "Chicago", "Houston", "Miami")
    val passwordRegex = "^(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>])(?=.*[0-9]).{8,}$".toRegex()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Create Your Account",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // TextField de FirstName
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF2274A5),
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // TextField de LastName
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF2274A5),
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // TextField de Email
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF2274A5),
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = !passwordRegex.matches(it)
                },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                isError = passwordError,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF2274A5),
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            // Mensaje de error si la contraseña no cumple con los requisitos
            if (passwordError) {
                Text(
                    text = "Password must contain at least 1 uppercase letter, 1 symbol, and 1 number",
                    color = Color.Red,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            VerticalSpacer(16)

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
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                visualTransformation = VisualTransformation.None,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color(0xFF2274A5),
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Menú desplegable para la ciudad
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { expanded = true }
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
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

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(text = city) },
                        onClick = {
                            selectedCity = city
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Casilla de verificación de aviso de privacidad
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = privacyAccepted,
                    onCheckedChange = { privacyAccepted = it },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF2274A5))
                )
                Spacer(modifier = Modifier.width(8.dp))
                // Texto con parte subrayada para "Privacy Policy"
                val annotatedString = buildAnnotatedString {
                    append("I agree to the ")
                    withStyle(style = androidx.compose.ui.text.SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("Privacy Policy")
                    }
                }
                Text(
                    text = annotatedString,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    modifier = Modifier.clickable { showPrivacyPolicy = true }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            Button(
                onClick = { /* Acción de registro */ },
                enabled = privacyAccepted,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2274A5)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Register", fontSize = 18.sp, color = Color.White)
            }
        }

        // Modal del aviso de privacidad
        if (showPrivacyPolicy) {
            PrivacyPolicyDialog(onDismiss = { showPrivacyPolicy = false })
        }
    }
}

@Composable
fun PrivacyPolicyDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Close")
            }
        },
        text = {
            Column {
                Text(
                    text = "Privacy Policy",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Here is the detailed privacy policy of our app. Please read carefully. " +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ultrices dapibus arcu, " +
                            "sit amet gravida mauris tincidunt nec. Phasellus ac mauris quam."
                )
            }
        },
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Preview
@Composable
fun RegisterScreenUserPreview() {
    val navController = rememberNavController()
    RegisterScreenUser(navController)
}
