package com.example.spike.app.data.network.model

data class VetRegistrationData(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val street: String,
    val userType: String,
    val locality: String,
    val city: String,
    val cologne: String,
    val numInt: Int,
    val cp: Int,
    val rfc: String,
)
