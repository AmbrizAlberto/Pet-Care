package com.example.spike.presentation.ui.mainScreen

import android.graphics.Paint.Align
import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.spike.presentation.ui.categories
import com.example.spike.presentation.ui.mainScreen.components.BaseLayoutScreen
import com.example.spike.presentation.ui.theme.colorBlack
import com.example.spike.presentation.ui.theme.grayBackground
import com.example.spike.presentation.ui.theme.grayContent
import com.example.spike.presentation.ui.theme.white700

// Simulando listas de elementos para cada categoría
val categoryItems = listOf(
    listOf("Item A1", "Item A2", "Item A3"),
    listOf("Item B1", "Item B2", "Item B3"),
    listOf("Item C1", "Item C2", "Item C3"),
)

@Composable
fun PetCareCatalogueScreen(
//    onItemClick: (String) -> Unit,
    selectedItemIndexMenu: MutableState<Int>,
    navController: NavHostController,
) {
    val selectedIndex = remember {
        mutableIntStateOf(0)
    }


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
            VerticalSpacer(height = 20)
            SectionCategoryListHorizontal(selectedIndex = selectedIndex)
            VerticalSpacer(height = 20)
            CategoryItemList(items = categoryItems[selectedIndex.value])
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
        OutlinedTextField(
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
                .border(width = 2.dp, Color.Gray, shape = RoundedCornerShape(15.dp)),
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

@Composable
fun CategoryTitle(category: Any, isSelected: Boolean, modifier: Modifier) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = category.toString(),
            style = if (isSelected) {
                MaterialTheme.typography.titleMedium
            } else {
                MaterialTheme.typography.bodyLarge
            },
            color = white700
        )
    }

}

@Composable
fun SectionCategoryListHorizontal(selectedIndex: MutableState<Int>) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(categories) { index, item ->
            val isSelected = index == selectedIndex.value
            CategoryTitle(
                category = item,
                isSelected = isSelected,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = isSelected,
                        enabled = true,
                        onClick = { selectedIndex.value = index }
                    )
            )
        }
    }
}

@Composable
fun CategoryItemList(items: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Espaciado entre elementos
                    .clip(RoundedCornerShape(15.dp))
                    .background(grayContent)
                    .padding(20.dp), //Espaciado interior
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "imagen")
                    HorizontalSpacer(width = 10)
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                        color = white700,
                    )
                }
            }
        }
    }

}

@Composable
fun VerticalSpacer(height: Int) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun HorizontalSpacer(width: Int) {
    Spacer(modifier = Modifier.width(width.dp))
}

@Preview
@Composable
fun PetCareCataloguePreview() {
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
            VerticalSpacer(height = 20)
            SectionCategoryListHorizontal(selectedItemIndexMenu)
            VerticalSpacer(height = 20)
            CategoryItemList(items = categoryItems[selectedItemIndexMenu.value])
        }
    }
}