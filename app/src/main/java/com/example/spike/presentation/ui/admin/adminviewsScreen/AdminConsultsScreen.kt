
package com.example.spike.presentation.ui.adminviewsScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.spike.presentation.ui.adminviewsScreen.components.BaseLayoutAdminScreen
import com.example.spike.R
import com.example.spike.presentation.ui.adminviewsScreen.components.AdminOptionsMenu
import com.example.spike.presentation.ui.adminviewsScreen.components.AlertDialogExample
import com.example.spike.presentation.ui.adminviewsScreen.components.forms.EditConsultsBottomSheet

@Composable
fun AdminConsultsScreen() {
    BaseLayoutAdminScreen(title = "Administrar Consultas") {
        Spacer(modifier = Modifier.height(20.dp))
        ConsultsList()
    }
}

@Composable
fun ConsultsList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 21.dp, vertical = 8.dp)
    ) {
        items(3) {
            ConsultsCard()
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ConsultsCard() {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showConsultsEditForm by remember { mutableStateOf(false) }
    var showViewDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(0xFF39434F), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF39434F)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_pet),
                contentDescription = "Imagen de la mascota",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Consulta #01",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Pelusa",
                    color = Color(0xFF56B8FF),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 0.6.dp)
                )
                Text(
                    text = "Corte de pelo",
                    color = Color(0xFFCBC76F),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 0.6.dp)
                )
                Text(
                    text = "Veterinaria", // Tipo de consulta
                    color = Color(0xFFFFFFFF),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 0.8.dp)
                )
                Text(
                    text = "23 de agosto", // Fecha de la consulta
                    color = Color(0xFF95EE75),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 0.8.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Box(
                    modifier = Modifier
                        .offset(x = (5).dp, y = (-15).dp)
                ) {
                    AdminOptionsMenu(
                        onDelete = { showDeleteDialog = true },
                        onEdit = { showConsultsEditForm = true },
                        onView = { showViewDialog = true },

                        )
                }
                Spacer(modifier = Modifier.height(30.dp))
                TextButton(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.height(33.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0x7EFFC107)
                    )
                ) {
                    Text(
                        text = "Pendiente",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }

    if (showConsultsEditForm) {
        EditConsultsBottomSheet(
            isVisible = true,
            onDismiss = { showConsultsEditForm = false },
            onConfirm = { consult, petname, petservice, vetname, date ->
                println("Guardando cambios: $consult, $petname, $petservice, $vetname, $date")
                showConsultsEditForm = false
            }
        )
    }

    if (showDeleteDialog) {
        AlertDialogExample(
            onDismissRequest = { showDeleteDialog = false },
            onConfirmation = {
                showDeleteDialog = false
            },
            dialogTitle = "Confirmar eliminación",
            dialogText = "¿Estás seguro de que quieres eliminar esta Consulta?"
        )
    }

    if (showViewDialog) {
        AlertDialogExample(
            onDismissRequest = { showViewDialog = false },
            onConfirmation = { showViewDialog = false },
            dialogTitle = "Ver detalles del cliente",
            dialogText = "Aquí se mostrarían los detalles completos esta Consulta?"
        )
    }

}
@Preview(showBackground = true)
@Composable
fun AdminConsultsScreenPreview() {
    AdminConsultsScreen()
}