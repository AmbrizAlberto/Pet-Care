package com.example.spike.data.network

import com.example.spike.data.network.service.VetService
import com.example.spike.data.network.service.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api-spikeapp.vercel.app/"

object RetrofitInstance {
    val loginService: LoginService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }
    val vetService: VetService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VetService::class.java)
    }
}
