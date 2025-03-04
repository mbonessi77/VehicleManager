package com.example.vehiclemanagement.network

import com.example.vehiclemanagement.network.models.Record
import com.example.vehiclemanagement.network.models.RecordListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VehiclesAPI {
    @GET("vehicles")
    suspend fun getAllVehicles(@Query("start_cursor") cursor: String?): Response<RecordListResponse>

    @GET("vehicles/{id}")
    suspend fun getVehicleInto(@Path("id") id: Int): Response<Record>
}