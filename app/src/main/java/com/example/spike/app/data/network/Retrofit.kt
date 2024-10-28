package com.example.spike.app.data.network

import com.example.spike.app.data.network.service.LoginService
import com.example.spike.app.data.network.service.RegisterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api-spike-indol.vercel.app/"

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
    val registerService: RegisterService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterService::class.java)
    }
}
