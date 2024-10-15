package com.example.spike.app.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "https://api-spike-indol.vercel.app/"

data class LoginRequest(
    val email: String,
    val password: String)

data class User(
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

data class LoginResponse(
    val user: User,
    val token: String?
)

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
