package com.example.vehiclemanagement.network.models

import com.google.gson.annotations.SerializedName

open class Filter {
    data class Label(@SerializedName("include") val include: IncludeFilter) : Filter()
    data class NameFilter(
        @SerializedName(
            "name", alternate = ["license_plate", "vin"]
        ) val name: LikeFilter
    ) : Filter()
}

data class LikeFilter(
    @SerializedName("like") val like: String
)

data class IncludeFilter(
    @SerializedName("include") val include: String
)