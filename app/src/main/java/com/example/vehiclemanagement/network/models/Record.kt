package com.example.vehiclemanagement.network.models

import com.google.gson.annotations.SerializedName

data class Record(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("account_id")
    val accountId: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("ownership")
    val ownership: String = "",
    @SerializedName("vehicle_type_name")
    val vehicleTypeName: String = ""
)
