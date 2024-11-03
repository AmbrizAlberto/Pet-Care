package com.example.spike.data.network.model

data class User (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val role: String,
    val city: String,
    val number_int: Int,
    val cp: Int,
    val createdAt: String,
    val updatedAt: String
)