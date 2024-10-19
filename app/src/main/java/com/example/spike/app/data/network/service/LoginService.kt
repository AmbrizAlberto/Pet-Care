package com.example.spike.app.data.network.service

import com.example.spike.app.data.network.model.LoginRequest
import com.example.spike.app.data.network.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}