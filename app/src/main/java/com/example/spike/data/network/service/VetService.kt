package com.example.spike.data.network.service

import com.example.spike.data.network.model.requests.GetVetsRequest
import com.example.spike.data.network.model.responses.GetVetsResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface VetService {
//    Servicio para listados (veterinarias, usuarios, etc..)

    @POST("getveterinaries")
    suspend fun getVeterinaries(@Body request: GetVetsRequest): GetVetsResponse
}