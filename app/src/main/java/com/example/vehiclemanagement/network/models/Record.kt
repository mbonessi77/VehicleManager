package com.example.vehiclemanagement.network.models

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("id") val id: Int,
    @SerializedName("fuel_type_name") val fuelTypeName: String? = null,
    @SerializedName("group_name") val groupName: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("ownership") val ownership: String,
    @SerializedName("vehicle_type_name") val vehicleTypeName: String,
    @SerializedName("vehicle_status_name") val vehicleStatusName: String,
    @SerializedName("vehicle_status_color") val vehicleStatusColor: String,
    @SerializedName("primary_meter_unit") val primaryMeterUnit: String,
    @SerializedName("primary_meter_value") val primaryMeterValue: String,
    @SerializedName("color") val color: String? = null,
    @SerializedName("license_plate") val licensePlate: String? = null,
    @SerializedName("make") val make: String? = null,
    @SerializedName("model") val model: String? = null,
    @SerializedName("registration_state") val registrationState: String? = null,
    @SerializedName("trim") val trim: String? = null,
    @SerializedName("vin") val vin: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("default_image_url_small") val defaultImageUrlSmall: String? = null,
    @SerializedName("default_image_url_large") val defaultImageUrlLarge: String? = null,
    @SerializedName("specs") val specs: VehicleSpecs,
)
