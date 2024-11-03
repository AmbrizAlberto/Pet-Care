package com.example.spike.presentation.ui.user.appointmentsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.spike.data.network.model.Appointment
import com.example.spike.presentation.ui.theme.darkCementPalette
import com.example.spike.presentation.ui.theme.grayContent

@Composable
fun AppointmentsScreen(navController: NavHostController) {
//        Simulación de lista de citas pendientes
    val appointments = listOf(
        Appointment(
            "Dr. Martin Serna",
            "Veterinaria 1",
            "Calle 1, Colonia Centro, Mzo, Col.",
            "1 de diciembre",
            "14:00"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Pending Appointments",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(appointments) { appointment ->
                AppointmentItem(appointment)
            }
        }
    }
}

@Composable
fun AppointmentItem(appointment: Appointment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(darkCementPalette)
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = "Appointment with ${appointment.vetName}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${appointment.date}, ${appointment.time}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun AppointmentsScreenPreview() {

}