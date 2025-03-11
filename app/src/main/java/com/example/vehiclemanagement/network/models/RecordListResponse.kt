package com.example.vehiclemanagement.network.models

import com.google.gson.annotations.SerializedName

data class RecordListResponse(
    @SerializedName("start_cursor") val startCursor: String,
    @SerializedName("next_cursor") val nextCursor: String?,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("estimated_remaining_count") val estimatedRemainingCount: Int,
    @SerializedName("filtered_by") val filteredBy: List<Filter>,
    @SerializedName("sorted_by") val sortedBy: List<Sort>,
    @SerializedName("records") val records: List<Record>
)



data class Sort(
    @SerializedName("id") val id: String
)

