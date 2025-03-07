package com.example.vehiclemanagement.network.models

import com.google.gson.annotations.SerializedName

data class VehicleSpecs(
    @SerializedName("id") val id: Int,
    @SerializedName("vehicle_id") val vehicleId: Int,
    @SerializedName("account_id") val accountId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("body_type") val bodyType: String? = null,
    @SerializedName("drive_type") val driveType: String? = null,
    @SerializedName("engine_description") val engineDescription: String? = null,
    @SerializedName("engine_brand") val engineBrand: String? = null,
    @SerializedName("fuel_quality") val fuelQuality: String? = null,
    @SerializedName("max_hp") val maxHp: String? = null,
    @SerializedName("max_torque") val maxTorque: String? = null,
    @SerializedName("oil_capacity") val oilCapacity: String? = null,
    @SerializedName("transmission_type") val transmissionType: String? = null,
    @SerializedName("cargo_volume") val cargoVolume: String? = null,
    @SerializedName("msrp") val msrp: String? = null
)