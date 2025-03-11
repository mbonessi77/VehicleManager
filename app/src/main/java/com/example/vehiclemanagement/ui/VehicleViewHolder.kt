package com.example.vehiclemanagement.ui

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.databinding.VehicleItemViewBinding
import com.example.vehiclemanagement.network.models.Record

class VehicleViewHolder(private val rootView: VehicleItemViewBinding) :
    RecyclerView.ViewHolder(rootView.root) {
    fun bind(record: Record?) {
        rootView.vehicle = record
        rootView.statusColor.setBackgroundColor(getStatusColor(record?.vehicleStatusColor))
    }

    private fun getStatusColor(colorValue: String?): Int {
        return when (colorValue) {
            "green" -> Color.GREEN
            "red" -> Color.RED
            "orange" -> Color.YELLOW
            else -> Color.WHITE
        }
    }
}