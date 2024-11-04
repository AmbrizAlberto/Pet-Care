package com.example.spike.data.network.service


import com.example.spike.data.network.model.responses.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RegisterService {
    @Multipart
    @POST("createVeterinary")
    suspend fun registerVet(
        @Part("veterinarieName") veterinarieName: RequestBody,
        @Part("street") street: RequestBody,
        @Part("email") email: RequestBody,
        @Part("phone") phone: RequestBody,
        @Part("password") password: RequestBody,
        @Part("role") role: RequestBody,
        @Part("city") city: RequestBody,
        @Part("locality") locality: RequestBody,
        @Part("cologne") cologne: RequestBody,
        @Part("number_int") number_int: RequestBody,
        @Part("cp") cp: RequestBody,
        @Part("rfc") rfc: RequestBody,
        @Part img: MultipartBody.Part, // Para la imagen
        @Part("category") category: RequestBody,
        @Part("horaInicio") horaIncio: RequestBody, // Agregado
        @Part("horaFin") horaFin: RequestBody, // Agregado
        @Part("diasSemana") openingDaysBody: RequestBody // Agregado
    ): Response<RegisterResponse>
}

