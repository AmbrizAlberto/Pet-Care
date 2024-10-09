package com.example.spike.presentation.ui

import androidx.annotation.DrawableRes
import com.example.spike.R

data class Menu(val id: Int, val title: String, @DrawableRes val icon: Int, val url: String)

val categories = listOf<Any>(
    "Nutrición", "Atención", "Reacreación",
)

val menuItems: List<Menu> = listOf(
    Menu(1, "Home", R.drawable.ic_home, "home"),
    Menu(2, "Profile", R.drawable.ic_user, "profile"),
)

