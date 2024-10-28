package com.example.spike.presentation.ui.shared.screenRegister


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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.shared.screenRegister.RegisterViewModel

@Composable
fun ReviewScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
    userType: String?,
    nameVet: String?,
    street: String?,
    email: String?,
    phone: String?,
    password: String?,
    city: String?,
    locality: String?,
    cologne: String?,
    numInt: String?,
    cp: String?,
    rfc: String?
) {
    // Tu lógica de UI aquí

    // Imprimir valores en logs
    Log.d("Review", "User Type: $userType")
    Log.d("Review", "Street: $street")
    Log.d("Review", "Email: $email")
    Log.d("Review", "Name: $nameVet")
    Log.d("Review", "Phone: $phone")
    Log.d("Review", "Password: $password")
    Log.d("Review", "Role: $userType")
    Log.d("Review", "City: $city")
    Log.d("Review", "Locality: $locality")
    Log.d("Review", "Cologne: $cologne")
    Log.d("Review", "Number Int: $numInt")
    Log.d("Review", "CP: $cp")
    Log.d("Review", "RFC: $rfc")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF243748)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Review Your Registration", color = Color.White, fontSize = 24.sp)

            Text("User Type: $userType", color = Color.White)
            Text("Name: $nameVet", color = Color.White)
            Text("Email: $email", color = Color.White)
            Text("Phone: $phone", color = Color.White)
            Text("Street: $street", color = Color.White)
            Text("Locality: $locality", color = Color.White)
            Text("Cologne: $cologne", color = Color.White)
            Text("Number Int: $numInt", color = Color.White)
            Text("Postal Code: $cp", color = Color.White)
            Text("RFC: $rfc", color = Color.White)
            Text("City: $city", color = Color.White)

            // Botones para navegar a diferentes pantallas según el userType
            Button(
                onClick = {
                    if (userType == "PET_OWNER") {
                        navController.navigate("user_register")
                    } else if (userType == "VETERINARY_OWNER") {
                        navController.navigate("vet_register")
                    }
                }
            ) {
                Text("Proceed", color = Color.White)
            }
        }
    }
}



