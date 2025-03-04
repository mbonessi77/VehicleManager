package com.example.vehiclemanagement.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.databinding.VehicleItemViewBinding
import com.example.vehiclemanagement.network.models.Record

class VehicleViewHolder(private val rootView: VehicleItemViewBinding) :
    RecyclerView.ViewHolder(rootView.root) {
    fun bind(record: Record) {
        rootView.vehicleRecord = record
    }
}