package com.example.vehiclemanagement.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FleetioNetwork {
    private const val BASE_URL = "https://secure.fleetio.com/api/v1/"

    fun getInstance(): Retrofit {
        val client = OkHttpClient.Builder().addNetworkInterceptor(RequestInterceptor()).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}