package com.example.spike.presentation.ui.user.profileScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spike.R
import com.example.spike.presentation.navigation.Destination
import com.example.spike.presentation.ui.user.mainScreen.VerticalSpacer
import com.example.spike.presentation.ui.user.mainScreen.components.BaseLayoutScreen
import com.example.spike.presentation.ui.theme.colorLight
import com.example.spike.presentation.ui.theme.darkCementPalette
import com.example.spike.presentation.ui.theme.white700
import com.example.spike.utils.handleLogout

@Composable
fun ProfileScreen(
    navController: NavHostController,
    selectedItemIndexMenu: MutableState<Int>,
) {
    BaseLayoutScreen(
        navController = navController,
        selectedItemIndexMenu = selectedItemIndexMenu
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProfileHeader()

            VerticalSpacer(24)

            ProfileOptions(navController)
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
//                .background(Color.LightGray)
            contentScale = ContentScale.Crop
        )

        VerticalSpacer(height = 12)

        Text(
            text = "Fulanita",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = colorLight
        )
        Text(
            text = "fulanito@gmail.com",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
        )
    }
}


@Composable
fun ProfileOptions(navController: NavHostController) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxWidth()) {
        ProfileOptionItem("Edit profile", onClick = { })
        ProfileOptionItem("My appointments", onClick = { })
        ProfileOptionItem("Settings", onClick = { })
        ProfileOptionItem("Logout", onClick = { handleLogout(navController, context) })
    }
}

@Composable
fun ProfileOptionItem(optionText: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(darkCementPalette)
            .clickable { onClick() }
            .padding(vertical = 22.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = optionText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
            fontSize = 18.sp,
            color = colorLight
        )
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForward,
            contentDescription = "Navigate",
            tint = white700,
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val navController = rememberNavController()
    val selectedItemIndexMenu: MutableState<Int> = remember { mutableIntStateOf(0) }

    BaseLayoutScreen(
        navController = navController,
        selectedItemIndexMenu = selectedItemIndexMenu
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ProfileHeader()

            VerticalSpacer(24)

            ProfileOptions(navController)
        }
    }
}