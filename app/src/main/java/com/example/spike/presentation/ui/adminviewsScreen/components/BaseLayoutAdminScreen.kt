package com.example.spike.presentation.ui.adminviewsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.spike.R


@Composable
fun BaseLayoutAdminScreen(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B3340))
    ) {
        TopBar(title = title)
        SearchLayout()
        Spacer(modifier = Modifier.height(16.dp))

        // contenido adicional
        content()
    }
}

@Composable
fun TopBar(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_menu),
            contentDescription = "Menú",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-10).dp) // Mueve el ícono 8dp a la izquierda
        )


        Text(
            text = title,
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }

    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
fun SearchLayout() {
    TextField(
        value = "",
        onValueChange = { /* Acción de búsqueda */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(51.dp)
            .padding(horizontal = 18.dp)
            .border(0.8.dp, Color.White, RoundedCornerShape(8.dp))
            .background(Color(0xFF4F545C), RoundedCornerShape(8.dp)),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search), // Icono de lupa
                contentDescription = "Buscar",
                tint = Color.White
            )
        },
        placeholder = {
            Text(text = "Search", color = Color.White.copy(alpha = 0.7f))
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun BaseLayoutAdminScreenPreview() {
    BaseLayoutAdminScreen(title = "Administrar") {
        Text(text = "  ", color = Color.White)
    }
}