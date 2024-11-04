package com.example.spike.data.network

import com.example.spike.data.network.service.RegisterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // Para mantener una instancia única durante el ciclo de vida de la app
object NetworkModule {

    private const val BASE_URL = "https://api-spikeapp.vercel.app/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        // Añadir interceptor de logging
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        // Añadir el cliente OkHttp con el interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)  // Usar el cliente con el interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRegisterService(retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}
