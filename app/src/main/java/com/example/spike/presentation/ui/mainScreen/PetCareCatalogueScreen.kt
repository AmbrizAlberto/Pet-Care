package com.example.spike.presentation.ui.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import com.example.spike.R
import com.example.spike.presentation.ui.mainScreen.components.BaseLayoutScreen
import com.example.spike.presentation.ui.mainScreen.theme.colorBlack
import com.example.spike.presentation.ui.mainScreen.theme.white700

@Composable
fun PetCareCatalogueScreen(
    onItemClick: (String) -> Unit,
    selectedItemIndexMenu: MutableState<Int>,
    navController: NavHostController,
) {
    BaseLayoutScreen(
        navController = navController,
        selectedItemIndexMenu = selectedItemIndexMenu
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 25.dp,
                    bottom = 15.dp,
                    end = 15.dp
                )
        ) {

        }
    }
}

@Composable
fun TopBarSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Arrow left",
            tint = white700,
            modifier = Modifier.size(24.dp)
        )
        Box(
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .border(
                    width = 1.dp,
                    color = Color(0x99FF594F),
//                    shape = RoundedCornerShape(50.dp)
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_user),
                contentDescription = "Profile",
                modifier = Modifier.fillMaxSize(),
                tint = white700
            )
        }

    }
}

@Composable
fun SearchSection() {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
                    tint = colorBlack,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .border(width = 0.dp, Color.White, shape = RoundedCornerShape(15.dp)),
            shape = RoundedCornerShape(15.dp),
            placeholder = {
                Text(
                    text = "Search...",
                    style = MaterialTheme.typography.labelMedium
                )
            },
//            colors = TextFieldColors(
//                focusedContainerColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                ...
//            )

        )
    }
}


@Preview
@Composable
fun TopBarSectionPreview() {
    val selectedItemIndexMenu: MutableState<Int> = remember { mutableStateOf(0) }
    val navController = rememberNavController()

    BaseLayoutScreen(
        navController = navController,
        selectedItemIndexMenu = selectedItemIndexMenu
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 25.dp,
                    bottom = 15.dp,
                    end = 15.dp
                )
        ) {
            TopBarSection()
            SearchSection()
        }
    }
}