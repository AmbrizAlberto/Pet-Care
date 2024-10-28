package com.example.spike.data.network.model.responses

import com.example.spike.data.network.model.User

data class LoginResponse(
    val user: User,
    val token: String?
)
