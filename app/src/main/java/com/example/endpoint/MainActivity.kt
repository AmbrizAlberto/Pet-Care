package com.example.endpoint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.endpoint.ui.theme.EndpointTheme
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EndpointTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UserList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UserList(modifier: Modifier = Modifier) {
    var users by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        fetchUsers { fetchedUsers ->
            users = fetchedUsers
        }
    }

    LazyColumn(modifier = modifier) {
        items(users) { user ->
            Text(text = user)
        }
    }
}

fun fetchUsers(onResult: (List<String>) -> Unit) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("https://api-spike-indol.vercel.app/messages")
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
                    val userList = mutableListOf<String>()
                    for (i in 0 until jsonArray.length()) {
                        val user = jsonArray.getJSONObject(i).getString("name")
                        userList.add(user)
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

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    EndpointTheme {
        UserList()
    }
}