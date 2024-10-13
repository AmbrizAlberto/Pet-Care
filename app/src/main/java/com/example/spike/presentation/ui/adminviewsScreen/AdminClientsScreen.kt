
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



@Composable
fun AdminClientsScreen() {
    BaseLayoutAdminScreen(title = "Administrar Clientes") {
        Spacer(modifier = Modifier.height(20.dp))
    }
}




@Preview(showBackground = true)
@Composable
fun AdminClientsScreenPreview() {
    AdminClientsScreen()


}
