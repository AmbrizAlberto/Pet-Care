package com.example.spike.presentation.ui.shared.screenLogin.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun FiveSidedShape() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val path = Path().apply {
            // Definir el polígono con bordes redondeados
            moveTo(size.width / 2, size.height * 0.25f) // Bajar la punta superior
            lineTo(0f, size.height * 0.5f) // Punto izquierdo superior
            lineTo(0f, size.height) // Punto inferior izquierdo
            lineTo(size.width, size.height) // Punto inferior derecho
            lineTo(size.width, size.height * 0.5f) // Punto derecho superior

            // Redondear la punta superior
            lineTo(
                size.width / 2 + 25.dp.toPx(),
                size.height * 0.25f + 25.dp.toPx()
            ) // Ajustar para la esquina redondeada
            lineTo(
                size.width / 2 - 25.dp.toPx(),
                size.height * 0.25f + 25.dp.toPx()
            ) // Ajustar para la esquina redondeada

            close() // Cerrar la figura
        }

        // Dibujar el polígono
        drawPath(
            path = path,
            color = Color(0xFF4C526A)
        )
    }
}