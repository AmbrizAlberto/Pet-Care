package com.example.spike.utils

import android.content.Context
import com.example.spike.utils.enums.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(SharedPreferences.USER_PREFS.value, Context.MODE_PRIVATE)
        return sharedPreferences.getString(SharedPreferences.TOKEN.value, null)
}