package com.example.vehiclemanagement.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.databinding.VehicleItemViewBinding
import com.example.vehiclemanagement.network.models.Record

class VehicleAdapter(
    private val records: List<Record>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<VehicleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        return VehicleViewHolder(
            VehicleItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = records.size

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(records[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onVehicleClicked(records[position].id!!)
        }
    }
}