package com.example.spike.presentation.ui.profileScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import com.example.spike.presentation.ui.mainScreen.components.BaseLayoutScreen

@Composable
fun ProfileScreen(
    navController: NavHostController,
    selectedItemIndexMenu: MutableState<Int>,
) {
    BaseLayoutScreen(
        navController = navController,
        selectedItemIndexMenu = selectedItemIndexMenu
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Profile",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.testTag("profileTag")
            )
        }
    }
}