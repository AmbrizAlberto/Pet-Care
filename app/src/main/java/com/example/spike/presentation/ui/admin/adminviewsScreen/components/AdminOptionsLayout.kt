
package com.example.spike.presentation.ui.adminviewsScreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background



import com.example.spike.R



@Composable
fun AdminOptionsMenu(
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onView: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {

        IconButton(onClick = { expanded = true }) {
            Icon(

                painter = painterResource(R.drawable.ic_options),
                modifier = Modifier
                    .size(30.dp),
                contentDescription = "Opciones",
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF39434F))
        ) {
            DropdownMenuItem(
                text = { Text("Ver", color = Color.White) },
                onClick = {
                    onView()
                    expanded = false
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_view),
                        contentDescription = "Ver",
                        tint = Color.White
                    )
                }
            )
            DropdownMenuItem(
                text = { Text("Editar", color = Color.White) },
                onClick = {
                    onEdit()
                    expanded = false
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "Editar",
                        tint = Color.White
                    )
                }
            )
            DropdownMenuItem(
                text = { Text("Eliminar", color = Color.White) },
                onClick = {
                    onDelete()
                    expanded = false
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),

                        contentDescription = "Eliminar",

                        tint = Color.White
                    )
                }
            )
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}


