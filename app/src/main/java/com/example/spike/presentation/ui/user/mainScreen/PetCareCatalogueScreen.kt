package com.example.spike.presentation.ui.user.mainScreen

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.spike.R
import com.example.spike.data.model.VetItemData
import com.example.spike.presentation.ui.categories
import com.example.spike.presentation.ui.theme.bluePalette
import com.example.spike.presentation.ui.user.mainScreen.components.BaseLayoutScreen
import com.example.spike.presentation.ui.theme.colorBlack
import com.example.spike.presentation.ui.theme.darkCementPalette
import com.example.spike.presentation.ui.theme.grayBackground
import com.example.spike.presentation.ui.theme.grayContent
import com.example.spike.presentation.ui.theme.white700

// Simulando listas de elementos para cada categoría
val categoryItems = listOf(
    VetItemData(
        name = "Pawfect Care",
        address = "123 Elm Street, Cityville",
        description = "Veterinaria general con atención de lunes a sábado.",
        imageRes = R.drawable.vet_example,
        category = "Atención"
    ),
    VetItemData(
        name = "Pawfect Care",
        address = "123 Elm Street, Cityville",
        description = "Veterinaria general con atención de lunes a sábado.",
        imageRes = R.drawable.vet_example,
        category = "Atención"
    ),
    VetItemData(
        name = "Pawfect Care",
        address = "123 Elm Street, Cityville",
        description = "Veterinaria general con atención de lunes a sábado.",
        imageRes = R.drawable.vet_example,
        category = "Atención"
    ),
    VetItemData(
        name = "Happy Paws Clinic",
        address = "45 Oak Avenue, Cityville",
        description = "Clínica especializada en mascotas pequeñas.",
        imageRes = R.drawable.vet_example,
        category = "Nutrición"
    ),
    VetItemData(
        name = "Healthy Tails",
        address = "67 Maple Boulevard, Townsville",
        description = "Ofrecemos cuidados preventivos y vacunas.",
        imageRes = R.drawable.vet_example,
        category = "Recreación"
    ),
    VetItemData(
        name = "Pet Experts",
        address = "89 Pine Road, Cityville",
        description = "Especialistas en cirugía y traumatología animal.",
        imageRes = R.drawable.vet_example,
        category = "Atención"
    ),
    VetItemData(
        name = "24/7 Vet Care",
        address = "456 Birch Lane, Cityville",
        description = "Atención de emergencias las 24 horas.",
        imageRes = R.drawable.vet_example,
        category = "Atención"
    )
)

fun filterByCategory(category: String, items: List<VetItemData>): List<VetItemData> {
    return items.filter { it.category == category }
}

@Composable
fun PetCareCatalogueScreen(
//    onItemClick: (String) -> Unit,
    selectedItemIndexMenu: MutableState<Int>,
    navController: NavHostController,
) {
    val selectedCategory = remember { mutableStateOf("Atención") }
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
            VerticalSpacer(height = 10)
//            SectionCategoryListHorizontal(selectedIndex = selectedIndex)
            CategoryFilterSection(selectedCategory)
            VerticalSpacer(height = 20)
            CategoryItemList(items = filterByCategory(selectedCategory.value, categoryItems))
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
fun CategoryFilterSection(selectedCategory: MutableState<String>) {
    val categories = listOf("Nutrición", "Atención", "Recreación")
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(categories) { category ->
            Text(
                text = category,
                modifier = Modifier
                    .padding(2.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (selectedCategory.value == category) bluePalette else darkCementPalette)
                    .padding(16.dp)
                    .clickable { selectedCategory.value = category }
                ,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun CategoryItemList(items: List<VetItemData>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 5.dp,
                bottom = 5.dp
            )
    ) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp) // Espaciado entre elementos
                    .clip(RoundedCornerShape(15.dp))
                    .background(darkCementPalette)
                    .padding(16.dp), //Espaciado interior
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    // Imagen de la veterinaria
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                    HorizontalSpacer(16)
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = item.address,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Text(
                            text = item.description,
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
//            SectionCategoryListHorizontal(selectedItemIndexMenu)
            CategoryFilterSection(selectedCategory)
            VerticalSpacer(height = 20)
            CategoryItemList(items = filterByCategory(selectedCategory.value, categoryItems))
        }
    }
}