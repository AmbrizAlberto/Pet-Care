import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spike.data.network.RetrofitInstance
import com.example.spike.data.network.model.Veterinary
import com.example.spike.data.network.model.requests.GetVetsRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserScreenViewModel : ViewModel() {

    // Estado interno MutableStateFlow privado para que solo el ViewModel lo modifique
    private val _veterinaries = MutableStateFlow<List<Veterinary>>(emptyList())

    // Estado público como StateFlow, para que sea observable desde la UI
    val veterinaries: StateFlow<List<Veterinary>> = _veterinaries

    // Manejo de mensajes de error
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Función para obtener las veterinarias desde la API
    fun fetchVeterinaries(token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstance.vetService.getVeterinaries(GetVetsRequest(token))

                // Actualizar el estado de las veterinarias con la respuesta
                _veterinaries.value = response.veterinaries
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
