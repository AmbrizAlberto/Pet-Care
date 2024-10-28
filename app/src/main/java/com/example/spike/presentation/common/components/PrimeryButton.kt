package com.example.spike.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spike.presentation.ui.theme.bluePalette

@Composable
fun PrimaryButton(text: String, onClickMethod: () -> Unit) {
    Button(
        onClick = onClickMethod,
        colors = ButtonDefaults.buttonColors(containerColor = bluePalette),
        modifier = Modifier
            .fillMaxWidth()
//            .padding(horizontal = 26.dp)
            .height(50.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text, fontSize = 18.sp, color = Color.White)
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    val text = "Primary"
    val onClick = {}

    PrimaryButton(text, onClick)
}