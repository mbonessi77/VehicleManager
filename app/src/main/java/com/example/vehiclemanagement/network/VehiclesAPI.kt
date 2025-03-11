package com.example.vehiclemanagement.network

import com.example.vehiclemanagement.network.models.RecordListResponse
import com.example.vehiclemanagement.network.models.Record
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface VehiclesAPI {
    @GET("vehicles")
    suspend fun getAllVehicles(
        @Query("start_cursor") cursor: String?,
        @QueryMap sortOptions: HashMap<String, String>?
    ): Response<RecordListResponse>

    @GET("vehicles/{id}")
    suspend fun getVehicleInfo(@Path("id") id: Int): Response<Record>

    @GET("labels")
    suspend fun getLabels(): Response<RecordListResponse>
}