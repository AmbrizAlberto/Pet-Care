package com.example.spike.presentation.ui.shared.screenRegister

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spike.R
import com.example.spike.data.network.service.RegisterService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.FileOutputStream

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerService: RegisterService,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var img = mutableStateOf<Uri?>(null)
    var veterinarieName = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var phone = mutableStateOf("")
    var street = mutableStateOf("")
    var role = mutableStateOf("")
    var number_int = mutableStateOf("")
    var locality = mutableStateOf("")
    var city = mutableStateOf("")
    var cologne = mutableStateOf("")
    var numberInt = mutableStateOf("")
    var cp = mutableStateOf("")
    var rfc = mutableStateOf("")
    var category = mutableStateOf("")
    var horaInicio = mutableStateOf("")
    var horaFin = mutableStateOf("")

    // Cambiamos a mutableStateListOf para que sea reactivo
    var diasSemana = mutableStateListOf<String>()  // Días seleccionados de la semana

    override fun toString(): String {
        return "RegisterViewModel(" +
                "img=${img.value}, " +
                "veterinarieName='${veterinarieName.value}', " +
                "email='${email.value}', " +
                "password='${password.value}', " +
                "phone='${phone.value}', " +
                "street='${street.value}', " +
                "role='${role.value}', " +
                "number_int='${number_int.value}', " +
                "locality='${locality.value}', " +
                "city='${city.value}', " +
                "cologne='${cologne.value}', " +
                "numberInt='${numberInt.value}', " +
                "cp='${cp.value}', " +
                "rfc='${rfc.value}', " +
                "category='${category.value}', " +
                "horaInicio='${horaInicio.value}', " +
                "horaFin='${horaFin.value}', " +
                "diasSemana='${diasSemana.joinToString(", ")}'" +
                ")"
    }

    fun updateSelectedImageUri(uri: Uri) {
        img.value = uri
    }

    // Función para actualizar los días seleccionados
    fun updateSelectedDay(day: String, isChecked: Boolean) {
        if (isChecked) {
            if (!diasSemana.contains(day)) {
                diasSemana.add(day)  // Agregar día si está marcado
            }
        } else {
            diasSemana.remove(day)  // Quitar día si está desmarcado
        }
    }

    fun isPasswordValid(password: String): Boolean {
        // Debe tener al menos 8 caracteres, una mayúscula, un número y un símbolo especial
        val passwordPattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,}\$"
        return Regex(passwordPattern).matches(password)
    }

    fun isEmailValid(email: String): Boolean {
        // Regex para validar email
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPhoneValid(phone: String): Boolean {
        // Valida el teléfono (ejemplo: debe tener 10 dígitos)
        return phone.length == 10 && phone.all { it.isDigit() }
    }

    fun validateFields(): Boolean {
        if (veterinarieName.value.isBlank()) {
            Log.e("RegisterViewModel", "El nombre de la veterinaria no puede estar vacío.")
            return false
        }
        if (email.value.isBlank()) {
            Log.e("RegisterViewModel", "El email no puede estar vacío.")
            return false
        }
        if (!isEmailValid(email.value)) {
            Log.e("RegisterViewModel", "El email no es válido.")
            return false
        }
        if (password.value.isBlank()) {
            Log.e("RegisterViewModel", "La contraseña no puede estar vacía.")
            return false
        }
        if (!isPasswordValid(password.value)) {
            Log.e("RegisterViewModel", "La contraseña no cumple con los requisitos.")
            return false
        }
        if (phone.value.isBlank()) {
            Log.e("RegisterViewModel", "El número de teléfono no puede estar vacío.")
            return false
        }
        if (!isPhoneValid(phone.value)) {
            Log.e("RegisterViewModel", "El número de teléfono no es válido.")
            return false
        }
        if (street.value.isBlank()) {
            Log.e("RegisterViewModel", "La calle no puede estar vacía.")
            return false
        }
        if (city.value.isBlank()) {
            Log.e("RegisterViewModel", "La ciudad no puede estar vacía.")
            return false
        }
        if (category.value.isBlank()) {
            Log.e("RegisterViewModel", "Debe seleccionar una categoría.")
            return false
        }
        if (horaInicio.value.isBlank()) {
            Log.e("RegisterViewModel", "Debe proporcionar una hora de inicio.")
            return false
        }
        if (horaFin.value.isBlank()) {
            Log.e("RegisterViewModel", "Debe proporcionar una hora de fin.")
            return false
        }
        if (diasSemana.isEmpty()) {
            Log.e("RegisterViewModel", "Debe seleccionar al menos un día de apertura.")
            return false
        }
        return true
    }

    fun registerVet() {
        // Validaciones (por ejemplo, asegurarse de que todos los campos requeridos no estén vacíos)
        if (!validateFields()) {
            return  // Detenemos el proceso si alguna validación falla
        }

        // Prepara los datos para la petición
        val diasSemanaBody = RequestBody.create("text/plain".toMediaTypeOrNull(), diasSemana.joinToString(","))
        val veterinarieNameBody = RequestBody.create("text/plain".toMediaTypeOrNull(), veterinarieName.value)
        val streetBody = RequestBody.create("text/plain".toMediaTypeOrNull(), street.value)
        val emailBody = RequestBody.create("text/plain".toMediaTypeOrNull(), email.value)
        val phoneBody = RequestBody.create("text/plain".toMediaTypeOrNull(), phone.value)
        val passwordBody = RequestBody.create("text/plain; charset=utf-8".toMediaTypeOrNull(), password.value)
        val roleBody = RequestBody.create("text/plain".toMediaTypeOrNull(), role.value)
        val cityBody = RequestBody.create("text/plain".toMediaTypeOrNull(), city.value)
        val localityBody = RequestBody.create("text/plain".toMediaTypeOrNull(), locality.value)
        val cologneBody = RequestBody.create("text/plain".toMediaTypeOrNull(), cologne.value)
        val number_intBody = RequestBody.create("text/plain".toMediaTypeOrNull(), number_int.value)
        val cpBody = RequestBody.create("text/plain".toMediaTypeOrNull(), cp.value)
        val rfcBody = RequestBody.create("text/plain".toMediaTypeOrNull(), rfc.value)
        val categoryBody = RequestBody.create("text/plain".toMediaTypeOrNull(), category.value)
        val horaFinBody = RequestBody.create("text/plain".toMediaTypeOrNull(), horaFin.value)
        val horaInicioBody = RequestBody.create("text/plain".toMediaTypeOrNull(), horaInicio.value)
        val imgPart = createImagePart()

        Log.d("RegisterViewModel", "Datos enviados: ${veterinarieName.value}, ${role.value}, ${email.value}, ${city.value}, ${cologne.value}, ${locality.value}, ${street.value}, ${numberInt.value}, ${password.value}, ${phone.value}, ${rfc.value}, ${cp.value}, ${diasSemana.joinToString(", ")}")

        viewModelScope.launch {
            try {
                val response = registerService.registerVet(
                    veterinarieNameBody,
                    streetBody,
                    emailBody,
                    phoneBody,
                    passwordBody,
                    roleBody,
                    cityBody,
                    localityBody,
                    cologneBody,
                    number_intBody,
                    cpBody,
                    rfcBody,
                    imgPart,
                    categoryBody,
                    horaInicioBody,
                    horaFinBody,
                    diasSemanaBody
                )
                if (response.isSuccessful) {
                    Log.d("RegisterViewModel", "Registro exitoso: ${response.message()}")
                    Log.d("Objeto", response.body().toString())  // Asegúrate de que el cuerpo no sea nulo
                } else {
                    Log.e("RegisterViewModel", "Error en el registro: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Excepción al registrar: ${e.message}")
            }
        }
    }

    private fun createImagePart(): MultipartBody.Part {
        return img.value?.let { uri ->
            val filePath = getRealPathFromURI(uri) ?: uri.path
            val file = File(filePath ?: "")
            if (file.exists()) {
                val requestFile = RequestBody.create("image/png".toMediaTypeOrNull(), file)
                MultipartBody.Part.createFormData("img", file.name, requestFile)
            } else {
                Log.e("RegisterViewModel", "El archivo de imagen no existe.")
                throw IllegalArgumentException("El archivo de imagen no existe.")
            }
        } ?: run {
            // Usa una imagen predeterminada desde la carpeta drawable
            val drawableId = R.drawable.img
            val bitmap = BitmapFactory.decodeResource(context.resources, drawableId)
            val defaultImageFile = File(context.cacheDir, "default_image.png")
            FileOutputStream(defaultImageFile).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }

            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), defaultImageFile)
            MultipartBody.Part.createFormData("img", defaultImageFile.name, requestFile)
        }
    }
    private fun getRealPathFromURI(uri: Uri): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.getString(columnIndex)
            } else {
                null
            }
        } ?: run {
            Log.e("RegisterViewModel", "No se pudo obtener la ruta real de la URI.")
            null
        }
    }

}



