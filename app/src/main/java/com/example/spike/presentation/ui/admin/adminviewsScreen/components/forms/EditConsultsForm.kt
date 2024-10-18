package com.example.spike.presentation.ui.adminviewsScreen.components.forms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.*
import com.example.spike.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditConsultsBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String, String, Calendar) -> Unit
) {
    var consult by remember { mutableStateOf("") }
    var petname by remember { mutableStateOf("") }
    var petservice by remember { mutableStateOf("") }
    var vetname by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }
    var showDatePicker by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val sheetPeekHeight = screenHeight * 0.8f

    if (isVisible) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

        LaunchedEffect(sheetState) {
            sheetState.expand()
        }

        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState,
            windowInsets = WindowInsets(0),
            dragHandle = { BottomSheetDefaults.DragHandle() },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(sheetPeekHeight)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Editar Consulta",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                CustomTextField(
                    value = consult,
                    onValueChange = { consult = it },
                    label = "Consulta",
                    icon = Icons.Default.Person
                )

                CustomTextField(
                    value = petname,
                    onValueChange = { petname = it },
                    label = "Nombre de la Mascota",
                    icon = Icons.Default.Phone,
                    keyboardType = KeyboardType.Phone
                )

                CustomTextField(
                    value = petservice,
                    onValueChange = { petservice = it },
                    label = "Servicio",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email
                )

                CustomTextField(
                    value = vetname,
                    onValueChange = { vetname = it },
                    label = "Veterinaria",
                    icon = Icons.Default.Home,

                    )

                DatePickerField(
                    selectedDate = selectedDate,
                    onDateSelected = { selectedDate = it },
                    onShowDatePicker = { showDatePicker = true }
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { onConfirm(consult, petname, petservice, vetname, selectedDate) }) {
                        Text("Guardar")
                    }
                }
            }
        }

        if (showDatePicker) {
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = selectedDate.timeInMillis
            )

            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                selectedDate.timeInMillis = millis
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancelar")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true
    )
}

@Composable
fun DatePickerField(
    selectedDate: Calendar,
    onDateSelected: (Calendar) -> Unit,
    onShowDatePicker: () -> Unit
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    OutlinedTextField(
        value = dateFormat.format(selectedDate.time),
        onValueChange = { },
        label = { Text("Fecha") },
        leadingIcon = { Icon(Icons.Default.DateRange, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = onShowDatePicker) {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select date")
            }
        }
    )
}