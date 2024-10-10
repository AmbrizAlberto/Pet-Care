package com.example.spike.presentation.ui.dashboardScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import com.example.spike.R

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B3340)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        // Top bar
        Row(
            modifier = Modifier
                .width(380.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Spacer para empujar los íconos al final (lado derecho)
            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier
                .height(1.dp)
                .width(350.dp)
                .background(Color(0xFF394A58))
        )


        Spacer(modifier = Modifier.height(40.dp))

        // Profile Section
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
                .background(Color(0xFFBAC9CF))
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatarimg),
                contentDescription = "Profile Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_editpen),
            contentDescription = "Edit Profile",
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
                .offset(x = 65.dp, y = -17.dp) // Ajuste de posición
        )

        Text(
            text = "Administrador",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(50.dp))

        // Icon Section
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp))
                .fillMaxWidth()
                .height(570.dp)
                .background(Color(0xFF677789))
                .padding(20.dp)
                .shadow(
                    elevation = 100.dp, // Define la altura de la sombra
                    shape = RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp), // Redondear la parte superior
                    clip = false // No recorta la sombra
                )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally)

            {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                )

                {
                    DashboardIcon(R.drawable.ic_clients, "Clientes")
                    DashboardIcon(R.drawable.ic_staff, "Personal")
                    DashboardIcon(R.drawable.ic_consultations, "Consultas")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DashboardIcon(R.drawable.ic_collaborators, "Colaboradores")
                    DashboardIcon(R.drawable.ic_services, "Servicios")
                    DashboardIcon(R.drawable.ic_reports, "Reportes")
                }
                Spacer(modifier = Modifier.height(30.dp))

                // Imagen final
                Image(
                    painter = painterResource(id = R.drawable.ic_dog),
                    contentDescription = "Dog Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(344.dp)
                        .height(115.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
        }
    }
}

@Composable
fun DashboardIcon(iconResId: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(9.dp)
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(Color(0xFF2B3340)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(59.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold

        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
