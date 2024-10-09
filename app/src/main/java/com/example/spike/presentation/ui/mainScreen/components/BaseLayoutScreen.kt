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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spike.presentation.ui.Menu
import com.example.spike.presentation.ui.mainScreen.SearchSection
import com.example.spike.presentation.ui.mainScreen.SectionCategoryListHorizontal
import com.example.spike.presentation.ui.mainScreen.Spacer
import com.example.spike.presentation.ui.mainScreen.TopBarSection
import com.example.spike.presentation.ui.theme.colorBlack
import com.example.spike.presentation.ui.theme.grayBackground
import com.example.spike.presentation.ui.theme.white700
import com.example.spike.presentation.ui.menuItems


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
        SectionMenuBottom(
            items = menuItems,
            selectedItemIndexMenu = selectedItemIndexMenu,
            navController = navController,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun SectionMenuBottom(
    modifier: Modifier = Modifier,
    items: List<Menu>,
    initialSelectedItemIndex: Int = 0,
    selectedItemIndexMenu: MutableState<Int>,
    navController: NavHostController,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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
                        MaterialTheme.typography.titleMedium
                    } else {
                        MaterialTheme.typography.bodySmall
                    },
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun BaseLayoutScreenPreview() {
    val selectedItemIndexMenu: MutableState<Int> = remember { mutableStateOf(0) }
    val navController = rememberNavController()

    BaseLayoutScreen(
        navController = navController,
        selectedItemIndexMenu = selectedItemIndexMenu
    ) {
//        Contenido
    }
}