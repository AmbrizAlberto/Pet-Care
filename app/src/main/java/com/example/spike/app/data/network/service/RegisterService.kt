package com.example.spike.app.data.network.service

import com.example.spike.app.data.model.RegisterResponse
import com.example.spike.app.data.network.model.VetRegistrationData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("/createVeterinary")
    suspend fun registerVet(
        @Body vetRegistrationData: VetRegistrationData
    ): Response<RegisterResponse>
}


