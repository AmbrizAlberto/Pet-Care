package com.example.endpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.endpoint.ui.theme.EndpointTheme
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EndpointTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("addUser") { AddUserScreen(navController) }
                    composable("deleteUser") { DeleteUserScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    var users by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("User Management") })
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                Button(onClick = {
                    fetchUsers { fetchedUsers ->
                        users = fetchedUsers
                    }
                }) {
                    Text("List Users")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate("addUser")
                }) {
                    Text("Add User")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    navController.navigate("deleteUser")
                }) {
                    Text("Delete User")
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(users) { user ->
                        Text(text = "ID: ${user.first}, Name: ${user.second}")
                    }
                }
            }
        }
    )
}

@Composable
fun AddUserScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current as ComponentActivity

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Add User") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = role,
                    onValueChange = { role = it },
                    label = { Text("Role") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(onClick = {
                        isLoading = true
                        errorMessage = null
                        createUser(context, firstName, lastName, email, phone, password, role) {
                            isLoading = false
                            navController.popBackStack()
                        }
                    }) {
                        Text("Submit")
                    }
                }
                errorMessage?.let {
                    Text(text = it, color = Color.Red)
                }
            }
        }
    )
}

@Composable
fun DeleteUserScreen(navController: NavHostController) {
    var userId by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current as ComponentActivity

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Delete User") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                OutlinedTextField(
                    value = userId,
                    onValueChange = { userId = it },
                    label = { Text("User ID") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(onClick = {
                        isLoading = true
                        errorMessage = null
                        deleteUser(context, userId) {
                            isLoading = false
                            navController.popBackStack()
                        }
                    }) {
                        Text("Delete")
                    }
                }
                errorMessage?.let {
                    Text(text = it, color = Color.Red)
                }
            }
        }
    )
}

fun fetchUsers(onResult: (List<Pair<String, String>>) -> Unit) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("http://10.0.2.2:3000/getUsers")
        .get()
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            println("Error en la solicitud: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                val responseBody = response.body
                if (responseBody != null) {
                    val responseData = responseBody.string()
                    println("Datos obtenidos: $responseData") // Ver qué datos recibimos
                    val jsonArray = JSONArray(responseData)
                    val userList = mutableListOf<Pair<String, String>>()
                    for (i in 0 until jsonArray.length()) {
                        val user = jsonArray.getJSONObject(i)
                        val id = user.getString("id")
                        val name = user.getString("firstName") + " " + user.getString("lastName")
                        userList.add(Pair(id, name))
                    }
                    println("Lista de usuarios: $userList")
                    onResult(userList)
                } else {
                    println("Error: El cuerpo de la respuesta es nulo")
                }
            } else {
                val errorCode = response.code
                println("Error: Código de respuesta HTTP $errorCode")
            }
        }
    })
}

fun createUser(
    context: ComponentActivity,
    firstName: String,
    lastName: String,
    email: String,
    phone: String,
    password: String,
    role: String,
    onResult: () -> Unit
) {
    val client = OkHttpClient()

    val json = """
        {
            "firstName": "$firstName",
            "lastName": "$lastName",
            "email": "$email",
            "phone": "$phone",
            "password": "$password",
            "role": "$role"
        }
    """.trimIndent()

    val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    val request = Request.Builder()
        .url("http://10.0.2.2:3000/createUser")
        .post(body)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            println("Error en la solicitud: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                println("Usuario creado exitosamente")
                // Ejecutar en el hilo principal
                context.runOnUiThread {
                    onResult()
                }
            } else {
                val errorCode = response.code
                val errorBody = response.body?.string()
                println("Error: Código de respuesta HTTP $errorCode, Cuerpo del error: $errorBody")
            }
        }
    })
}

fun deleteUser(
    context: ComponentActivity,
    userId: String,
    onResult: () -> Unit
) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("http://10.0.2.2:3000/deleteUser/$userId")
        .delete()
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
            println("Error en la solicitud: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                println("Usuario borrado exitosamente")
                // Ejecutar en el hilo principal
                context.runOnUiThread {
                    onResult()
                }
            } else {
                val errorCode = response.code
                val errorBody = response.body?.string()
                println("Error: Código de respuesta HTTP $errorCode, Cuerpo del error: $errorBody")
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EndpointTheme {
        HomeScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun AddUserScreenPreview() {
    EndpointTheme {
        AddUserScreen(rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteUserScreenPreview() {
    EndpointTheme {
        DeleteUserScreen(rememberNavController())
    }
}