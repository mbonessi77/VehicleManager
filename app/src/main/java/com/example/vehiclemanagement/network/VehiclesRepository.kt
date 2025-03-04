package com.example.vehiclemanagement.network

import com.example.vehiclemanagement.network.models.Record
import com.example.vehiclemanagement.network.models.RecordListResponse
import retrofit2.Response

class VehiclesRepository {
    private val networkService = FleetioNetwork.getInstance().create(VehiclesAPI::class.java)

    suspend fun getRecordsList(): Response<RecordListResponse> {
        return networkService.getAllVehicles()
    }

    suspend fun getVehicleData(id: Int): Response<Record> {
        return networkService.getVehicleInto(id)
    }
}