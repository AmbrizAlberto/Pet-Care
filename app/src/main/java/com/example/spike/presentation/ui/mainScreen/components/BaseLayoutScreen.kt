package com.example.spike.presentation.ui.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.spike.presentation.ui.Menu
import com.example.spike.presentation.ui.mainScreen.theme.colorBlack
import com.example.spike.presentation.ui.mainScreen.theme.grayBackground
import com.example.spike.presentation.ui.mainScreen.theme.white700


@Composable
fun BaseLayoutScreen(
    navController: NavHostController,
    selectedItemIndexMenu: MutableState<Int>,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(grayBackground)
    ) {
        content()
    }
}

@Composable
fun SectionMenuBottom(
    items: List<Menu>,
    initialSelectedItemIndex: Int = 0,
    selectedItemIndexMenu: MutableState<Int>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(colorBlack)
    ) {
        items.forEachIndexed { index, menu ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        selectedItemIndexMenu.value = index
                        navController.navigate(menu.url)
                    }

            ) {
                Icon(
                    painter = painterResource(id = menu.icon),
                    contentDescription = menu.title + index,
                    tint = if (index == selectedItemIndexMenu.value) {
                        Color.White
                    } else {
                        white700
                    }
                )
                Text(
                    text = menu.title,
                    style = if (index == selectedItemIndexMenu.value) {
                        MaterialTheme.typography.headlineMedium
                    } else {
                        MaterialTheme.typography.bodySmall
                    },
                    color = Color.White
                )
            }
        }
    }
}