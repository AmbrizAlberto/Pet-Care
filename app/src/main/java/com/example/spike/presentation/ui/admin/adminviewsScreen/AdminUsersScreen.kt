
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
            ClienteCard()
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Composable
fun ClienteCard() {
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
                painter = painterResource(R.drawable.profile),
                contentDescription = "Imagen del cliente",
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, CircleShape)
                    .size(35.dp)
                    .clip(CircleShape)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)

            ) {

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
                    text = "Veterinaria",
                    color = Color(0xFFCBC76F),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 0.6.dp)

                )
                Text(

                    text = "Mascotas",
                    color = Color(0xFFFFFFFF),
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
                        .background(Color.White)
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )


            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_options),
                    modifier = Modifier
                        .size(30.dp)
                        .offset(y = (-10).dp, x = (-2).dp),
                    contentDescription = "Opciones",
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(50.dp))
                TextButton(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.height(33.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color(0xFF5CB85C)
                    )
                ) {
                    Text(

                        text = "Verificado",
                        color = Color.White,
                        fontSize = 13.sp,



                        )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdminUsersScreenPreview() {
    AdminUsersScreen()


}
