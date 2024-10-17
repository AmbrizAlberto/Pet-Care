package com.example.spike.app.data.network.model

import com.example.spike.data.model.User

data class LoginResponse(
    val user: User,
    val token: String?
)
