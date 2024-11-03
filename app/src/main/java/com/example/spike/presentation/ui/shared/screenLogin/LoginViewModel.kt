package com.example.spike.presentation.ui.shared.screenLogin

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spike.data.network.model.responses.LoginResponse
import com.example.spike.data.network.model.requests.LoginRequest
import com.example.spike.data.network.RetrofitInstance
import com.example.spike.utils.enums.SharedPreferences
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    var loginState = mutableStateOf<LoginResponse?>(null)
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitInstance.loginService.login(LoginRequest(email, password))
                }
                if (response.token != null) {
                    val sharedPreferences =
                        context.getSharedPreferences(SharedPreferences.USER_PREFS.value, Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()) {
                        putString("user_token", response.token)
                        putString("user_role", response.user.role)
                        apply()
                    }
                    loginState.value = response

//                    Guardar
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




