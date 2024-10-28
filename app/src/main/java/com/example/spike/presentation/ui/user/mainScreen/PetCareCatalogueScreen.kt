package com.example.spike.presentation.ui.user.mainScreen

import UserScreenViewModel
import android.graphics.Paint.Align
import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.spike.R
import com.example.spike.data.network.model.Veterinary
import com.example.spike.presentation.ui.categories
import com.example.spike.presentation.ui.theme.bluePalette
import com.example.spike.presentation.ui.user.mainScreen.components.BaseLayoutScreen
import com.example.spike.presentation.ui.theme.colorBlack
import com.example.spike.presentation.ui.theme.darkCementPalette
import com.example.spike.presentation.ui.theme.graphitePalette
import com.example.spike.presentation.ui.theme.grayBackground
import com.example.spike.presentation.ui.theme.grayContent
import com.example.spike.presentation.ui.theme.white700
import com.example.spike.utils.getTokenFromSharedPreferences
import com.example.spike.utils.previews.veterinaryItems

fun filterByCategory(category: String, items: List<Veterinary>): List<Veterinary> {
    return items.filter { it.category.contains(category) }
}

@Composable
fun PetCareCatalogueScreen(
    selectedItemIndexMenu: MutableState<Int>,
    navController: NavHostController,
    userScreenViewModel: UserScreenViewModel = viewModel()
) {
    val selectedCategory = remember { mutableStateOf("NUTRITION") }
    val searchText = remember { mutableStateOf("") }
    val context = LocalContext.current
    val token = getTokenFromSharedPreferences(context)
    val veterinaries by userScreenViewModel.veterinaries.collectAsState()
    val errorMessage by userScreenViewModel.errorMessage.collectAsState()
    val isLoading by userScreenViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        token?.let { userScreenViewModel.fetchVeterinaries(it) }
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
            SearchSection(searchText)
            VerticalSpacer(height = 10)
            CategoryFilterSection(selectedCategory)
            VerticalSpacer(height = 20)

            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
            } else {
                CategoryItemList(filterByCategory(selectedCategory.value, veterinaries))
            }
        }
    }
}


@Composable
fun TopBarSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Arrow left",
            tint = white700,
            modifier = Modifier.size(24.dp)
        )

        Box(
            modifier = Modifier.size(40.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.svg_logo),
                contentDescription = "Logo",
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier.size(33.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pet),
                contentDescription = "Pet",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Composable
fun SearchSection(searchText: MutableState<String>) {
    Column(modifier = Modifier.padding(top = 20.dp)) {
        OutlinedTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Search",
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
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Gray,
                focusedLeadingIconColor = Color.Black,
                focusedIndicatorColor = Color.Black,
                unfocusedContainerColor = Color.Transparent,
                unfocusedLeadingIconColor = Color.Gray,

                )

        )
    }
}

@Composable
fun CategoryFilterSection(selectedCategory: MutableState<String>) {
    val categories = mapOf(
        "NUTRITION" to "Nutrition",
        "CARE" to "Care",
        "RECREATION" to "Recreation"
    )

    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories.keys.toList()) { category ->
            CategoryChip(
                category = category,
                displayText = categories[category] ?: category,
                isSelected = selectedCategory.value == category,
                onClick = { selectedCategory.value = category }
            )
        }
    }
}

@Composable
fun CategoryChip(category: String, displayText: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) bluePalette else darkCementPalette
    val textColor = if (isSelected) Color.White else white700

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayText,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
fun CategoryItemList(items: List<Veterinary>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 5.dp)
    ) {
        items(items) { item ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(darkCementPalette)
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Imagen de la veterinaria
                    AsyncImage(
                        model = item.img,
                        contentDescription = item.veterinarieName,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop,
                        error = painterResource(R.drawable.ic_options)
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.veterinarieName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "${item.street}, ${item.locality}, ${item.city}, ${item.cologne}, CP ${item.cp}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            text = item.email,
                            style = MaterialTheme.typography.bodyMedium,
                            color = white700,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
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
    val selectedCategory = remember { mutableStateOf("Atención") }
    val searchText = remember {
        mutableStateOf("")
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
            SearchSection(searchText)
            VerticalSpacer(height = 20)
            CategoryFilterSection(selectedCategory)
            VerticalSpacer(height = 20)
            CategoryItemList(veterinaryItems)
        }
    }
}