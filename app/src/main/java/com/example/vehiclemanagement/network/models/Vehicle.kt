package com.example.vehiclemanagement.network.models

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("id") val id: Int,
    @SerializedName("account_id") val accountId: Int,
    @SerializedName("archived_at") val archivedAt: String? = null,
    @SerializedName("fuel_type_id") val fuelTypeId: Int? = null,
    @SerializedName("fuel_type_name") val fuelTypeName: String? = null,
    @SerializedName("fuel_volume_units") val fuelVolumeUnits: String,
    @SerializedName("group_id") val groupId: Int? = null,
    @SerializedName("group_name") val groupName: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("ownership") val ownership: String,
    @SerializedName("current_location_entry_id") val currentLocationEntryId: Int? = null,
    @SerializedName("system_of_measurement") val systemOfMeasurement: String,
    @SerializedName("vehicle_type_id") val vehicleTypeId: Int,
    @SerializedName("vehicle_type_name") val vehicleTypeName: String,
    @SerializedName("is_sample") val isSample: Boolean,
    @SerializedName("vehicle_status_id") val vehicleStatusId: Int,
    @SerializedName("vehicle_status_name") val vehicleStatusName: String,
    @SerializedName("vehicle_status_color") val vehicleStatusColor: String,
    @SerializedName("primary_meter_unit") val primaryMeterUnit: String,
    @SerializedName("primary_meter_value") val primaryMeterValue: String,
    @SerializedName("primary_meter_date") val primaryMeterDate: String? = null,
    @SerializedName("primary_meter_usage_per_day") val primaryMeterUsagePerDay: String? = null,
    @SerializedName("secondary_meter_unit") val secondaryMeterUnit: String? = null,
    @SerializedName("secondary_meter_value") val secondaryMeterValue: String,
    @SerializedName("secondary_meter_date") val secondaryMeterDate: String? = null,
    @SerializedName("secondary_meter_usage_per_day") val secondaryMeterUsagePerDay: String? = null,
    @SerializedName("in_service_meter_value") val inServiceMeterValue: String? = null,
    @SerializedName("in_service_date") val inServiceDate: String? = null,
    @SerializedName("out_of_service_meter_value") val outOfServiceMeterValue: String? = null,
    @SerializedName("out_of_service_date") val outOfServiceDate: String? = null,
    @SerializedName("estimated_service_months") val estimatedServiceMonths: Int? = null,
    @SerializedName("estimated_replacement_mileage") val estimatedReplacementMileage: Int? = null,
    @SerializedName("estimated_resale_price_cents") val estimatedResalePriceCents: Int? = null,
    @SerializedName("fuel_entries_count") val fuelEntriesCount: Int,
    @SerializedName("service_entries_count") val serviceEntriesCount: Int,
    @SerializedName("service_reminders_count") val serviceRemindersCount: Int,
    @SerializedName("vehicle_renewal_reminders_count") val vehicleRenewalRemindersCount: Int,
    @SerializedName("comments_count") val commentsCount: Int,
    @SerializedName("documents_count") val documentsCount: Int,
    @SerializedName("images_count") val imagesCount: Int,
    @SerializedName("issues_count") val issuesCount: Int,
    @SerializedName("work_orders_count") val workOrdersCount: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("labels") val labels: List<Label> = emptyList(),
    @SerializedName("group_ancestry") val groupAncestry: String? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("license_plate") val licensePlate: String? = null,
    @SerializedName("make") val make: String? = null,
    @SerializedName("model") val model: String? = null,
    @SerializedName("registration_expiration_month") val registrationExpirationMonth: Int,
    @SerializedName("registration_state") val registrationState: String? = null,
    @SerializedName("trim") val trim: String? = null,
    @SerializedName("vin") val vin: String? = null,
    @SerializedName("year") val year: Int? = null,
    @SerializedName("default_image_url_small") val defaultImageUrlSmall: String? = null,
    @SerializedName("ai_enabled") val aiEnabled: Boolean,
    @SerializedName("assetable_type") val assetableType: String,
    @SerializedName("custom_fields") val customFields: Map<String, Any> = emptyMap(),
    @SerializedName("axle_config_id") val axleConfigId: Int? = null,
    @SerializedName("loan_account_number") val loanAccountNumber: String? = null,
    @SerializedName("loan_ended_at") val loanEndedAt: String? = null,
    @SerializedName("loan_interest_rate") val loanInterestRate: String? = null,
    @SerializedName("loan_notes") val loanNotes: String? = null,
    @SerializedName("loan_started_at") val loanStartedAt: String? = null,
    @SerializedName("loan_vendor_id") val loanVendorId: Int? = null,
    @SerializedName("loan_vendor_name") val loanVendorName: String? = null,
    @SerializedName("inspection_schedules_count") val inspectionSchedulesCount: Int,
    @SerializedName("warranties_count") val warrantiesCount: Int,
    @SerializedName("warranties_active_count") val warrantiesActiveCount: Int,
    @SerializedName("default_image_url") val defaultImageUrl: String? = null,
    @SerializedName("default_image_url_medium") val defaultImageUrlMedium: String? = null,
    @SerializedName("default_image_url_large") val defaultImageUrlLarge: String? = null,
    @SerializedName("driver") val driver: Map<String, Any> = emptyMap(),
    @SerializedName("specs") val specs: VehicleSpecs,
    @SerializedName("comments") val comments: List<Map<String, Any>> = emptyList(),
    @SerializedName("current_location_entry") val currentLocationEntry: Map<String, Any> = emptyMap(),
    @SerializedName("documents_including_nested_resources_count") val documentsIncludingNestedResourcesCount: Int
)
