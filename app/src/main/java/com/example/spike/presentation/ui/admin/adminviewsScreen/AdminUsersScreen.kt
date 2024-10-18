
package com.example.spike.presentation.ui.adminviewsScreen


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.spike.presentation.ui.adminviewsScreen.components.AdminOptionsMenu
import com.example.spike.presentation.ui.adminviewsScreen.components.AlertDialogExample
import com.example.spike.presentation.ui.adminviewsScreen.components.forms.EditUsersBottomSheet
import com.example.spike.R
import com.example.spike.presentation.ui.adminviewsScreen.components.forms.EditClientsBottomSheet


@Composable
fun AdminUsersScreen() {
    BaseLayoutAdminScreen(title = "Administrar Usuarios") {
        Spacer(modifier = Modifier.height(20.dp))
        UsersList()
    }
}


@Composable
fun UsersList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 21.dp, vertical = 8.dp)
    ) {
        items(3) { // Tarjetas de Clientes (4 en este caso)
            UsersCard()
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun UsersCard() {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUsersEditForm by remember { mutableStateOf(false) }
    var showViewDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color(0xFF39434F), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF39434F)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box( // Usamos un Box para posicionar los elementos
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopStart), // Los datos de la izquierda
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "Imagen del cliente",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Cliente 1",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                        Text(
                            text = "3141650607",
                            color = Color(0xFF56B8FF),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 0.6.dp)
                        )
                        Text(
                            text = "csamano@ucol.mx",
                            color = Color(0xFF56B8FF),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 0.6.dp)
                        )
                        Text(
                            text = "Veterinaria",
                            color = Color(0xFFCBC76F),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 0.6.dp)
                        )
                        Text(
                            text = "Mascotas",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 0.8.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_pet),
                            contentDescription = "Mascota 1",
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .size(35.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .offset(x = (280).dp, y = (-10).dp)
            ) {
                AdminOptionsMenu(
                    onDelete = { showDeleteDialog = true },
                    onEdit = { showUsersEditForm = true },
                    onView = { showViewDialog = true },
                )
            }

            TextButton(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(34.dp)
                    .width(90.dp)
                    .align(Alignment.BottomEnd), // Mover el botón "Pendiente" a la parte inferior derecha
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

    if (showUsersEditForm) {
        EditUsersBottomSheet(
            isVisible = true,
            onDismiss = { showUsersEditForm = false },
            onConfirm = { username, phone, email, vetname, userpet ->
                println("Guardando cambios: $username, $phone, $email, $vetname, $userpet")
                showUsersEditForm = false
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
            dialogText = "¿Estás seguro de que quieres eliminar este Usuario?"
        )
    }

    if (showViewDialog) {
        AlertDialogExample(
            onDismissRequest = { showViewDialog = false },
            onConfirmation = { showViewDialog = false },
            dialogTitle = "Ver detalles del cliente",
            dialogText = "Aquí se mostrarían los detalles completos del Usuario"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AdminUsersScreenPreview() {
    AdminUsersScreen()


}
