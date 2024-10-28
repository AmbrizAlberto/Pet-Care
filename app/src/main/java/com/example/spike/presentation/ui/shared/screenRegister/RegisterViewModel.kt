package com.example.spike.presentation.ui.shared.screenRegister

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spike.app.data.model.RegisterResponse
import com.example.spike.app.data.network.RetrofitInstance
import com.example.spike.app.data.network.model.VetRegistrationData
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    var userType = mutableStateOf<String?>(null)
    var nameVet = mutableStateOf<String?>(null)
    var email = mutableStateOf<String?>(null)
    var password = mutableStateOf<String?>(null)
    var phone = mutableStateOf<String?>(null)
    var street = mutableStateOf<String?>(null)
    var locality = mutableStateOf<String?>(null)
    var cologne = mutableStateOf<String?>(null)
    var numInt = mutableStateOf<String?>(null)
    var cp = mutableStateOf<String?>(null)
    var rfc = mutableStateOf<String?>(null)
    var city: MutableState<String?> = mutableStateOf(null)

    init {
        Log.d("RegisterViewModel", "Initialized with userType: $userType, nameVet: $nameVet")
    }

    // Cuando estableces los valores, también puedes registrar los datos
    fun setUserData(
        userType: String?,
        nameVet: String?,
        email: String?,
        password: String?,
        phone: String?,
        street: String?,
        locality: String?,
        cologne: String?,
        numInt: String?,
        cp: String?,
        rfc: String?,
        city: String?
    ) {
        this.userType.value = userType
        this.nameVet.value = nameVet
        this.email.value = email
        this.password.value = password
        this.phone.value = phone
        this.street.value = street
        this.locality.value = locality
        this.cologne.value = cologne
        this.numInt.value = numInt
        this.cp.value = cp
        this.rfc.value = rfc
        this.city.value = city

        Log.d("RegisterViewModel", "Set user data: $userType, $nameVet, $email, $phone, $street, $locality, $cologne, $numInt, $cp, $rfc, $city")
    }
}