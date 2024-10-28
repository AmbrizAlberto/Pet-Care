package com.example.spike.data.network.service

import com.example.spike.data.network.model.requests.LoginRequest
import com.example.spike.data.network.model.responses.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}