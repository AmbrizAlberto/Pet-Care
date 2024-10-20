
package com.example.spike.presentation.ui.adminviewsScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.adminviewsScreen.components.BaseLayoutAdminScreen
import com.example.spike.presentation.ui.adminviewsScreen.components.AlertDialogExample
import com.example.spike.presentation.ui.adminviewsScreen.components.AdminOptionsMenu
import com.example.spike.presentation.ui.adminviewsScreen.components.forms.EditClientsBottomSheet
import com.example.spike.R


@Composable
fun AdminClientsScreen() {
    BaseLayoutAdminScreen(title = "Administrar Clientes") {
        Spacer(modifier = Modifier.height(20.dp))
        ClientsList()
    }
}

@Composable
fun ClientsList() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 21.dp, vertical = 8.dp)
    ) {
        items(3) {
            ClientsCard()
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ClientsCard() {
    var isExpanded by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showClientsEditForm by remember { mutableStateOf(false) }
    var showViewDialog by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            )
            .background(Color(0xFF39434F), RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF39434F)),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 23.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Arana Pet Center",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "4.5 ⭐",
                        color = Color(0xFFFFFFFF),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 0.6.dp)
                    )
                    Text(
                        text = "3141650597",
                        color = Color(0xFF56B8FF),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 0.6.dp)
                    )
                    Text(
                        text = "aranapetcenter@gmail.com",
                        color = Color(0xFFFFFFFF),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 0.8.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.offset(x = (-8).dp, y = (-40).dp)

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(if (isExpanded) R.drawable.ic_arrowup else R.drawable.icon_arrowdown),
                            modifier = Modifier
                                .size(30.dp)
                                .rotate(rotation)
                                .clickable {
                                    isExpanded = !isExpanded
                                },
                            contentDescription = if (isExpanded) "Contraer" else "Expandir",
                            tint = Color.White
                        )
                        AdminOptionsMenu(
                            onDelete = { showDeleteDialog = true },
                            onEdit = { showClientsEditForm = true },
                            onView = { showViewDialog = true }
                        )
                    }
                }
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.aranapetcenter),
                        contentDescription = "Vet Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(350.dp)
                            .padding(start = 16.dp, top = 10.dp, end = 16.dp)
                            .clip(RoundedCornerShape(30.dp))
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 16.dp, top = 20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_location),
                            contentDescription = "Location Icon",
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Santa Carolina, Manzanillo, Col.",
                            color = Color(0xFFFFFFFF),
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                TextButton(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(34.dp)
                        .width(360.dp)
                        .padding(start = 225.dp),
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

    // Mostrar formulario de edición
    if (showClientsEditForm) {
        EditClientsBottomSheet(
            isVisible = true,
            onDismiss = { showClientsEditForm = false },
            onConfirm = { vetname, phone, email, address ->
                // Aquí iría la lógica para guardar los cambios
                println("Guardando cambios: $vetname, $phone, $email, $address")
                showClientsEditForm = false
            }
        )
    }

    if (showDeleteDialog) {
        AlertDialogExample(
            onDismissRequest = { showDeleteDialog = false },
            onConfirmation = {
                // Aquí simularíamos la eliminación
                showDeleteDialog = false
            },
            dialogTitle = "Confirmar eliminación",
            dialogText = "¿Estás seguro de que quieres eliminar este cliente?"
        )
    }

    if (showViewDialog) {
        AlertDialogExample(
            onDismissRequest = { showViewDialog = false },
            onConfirmation = { showViewDialog = false },
            dialogTitle = "Ver detalles del cliente",
            dialogText = "Aquí se mostrarían los detalles completos del cliente"
        )

    }
}
@Preview(showBackground = true)
@Composable
fun AdminClientsScreenPreview() {
    AdminClientsScreen()
}


