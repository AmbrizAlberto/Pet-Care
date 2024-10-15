package com.example.spike.presentation.ui.shared.screenLogin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spike.app.data.network.LoginRequest
import com.example.spike.app.data.network.LoginResponse
import com.example.spike.app.data.network.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    var loginState = mutableStateOf<LoginResponse?>(null)
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.login(LoginRequest(email, password))
                }
                if (response.token != null) {
                    loginState.value = response
                    println(response)
                } else {
                    errorMessage.value = "Login failed: "
                }
            } catch (e: Exception) {
                errorMessage.value = "Login failed due to an exception: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}




