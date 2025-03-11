package com.example.vehiclemanagement.network

import com.example.vehiclemanagement.network.models.RecordListResponse
import com.example.vehiclemanagement.network.models.Record
import retrofit2.Response

class VehiclesRepository {
    private val networkService = FleetioNetwork.getInstance().create(VehiclesAPI::class.java)

    suspend fun getRecordsList(
        cursor: String? = null,
        options: HashMap<String, String>? = null
    ): Response<RecordListResponse> {
        return networkService.getAllVehicles(cursor, options)
    }

    suspend fun getVehicleData(id: Int): Response<Record> {
        return networkService.getVehicleInfo(id)
    }

    suspend fun getLabels(): Response<RecordListResponse> {
        return networkService.getLabels()
    }
}