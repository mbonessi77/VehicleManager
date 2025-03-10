package com.example.vehiclemanagement.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.vehiclemanagement.databinding.FragmentDetailsBinding
import com.example.vehiclemanagement.network.models.Vehicle

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        observeViewModel()
        viewModel.getVehicleData(args.recordId)
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.vehicleRecord.observe(viewLifecycleOwner) {
            binding.record = it
            binding.statusIcon.setBackgroundColor(getStatusColor(it.vehicleStatusColor))
            updateDetails(it)
        }
    }

    private fun updateDetails(vehicle: Vehicle) {

        binding.meterDetails.apply {
            title = "Meter"
            value = vehicle.primaryMeterValue.plus(" ${vehicle.primaryMeterUnit}")
        }

        binding.groupDetails.apply {
            title = "Group"
            value = vehicle.groupName ?: "---"
        }

        binding.typeDetails.apply {
            title = "Type"
            value = vehicle.vehicleTypeName ?: "---"
        }

        binding.fuelDetails.apply {
            title = "Fuel Type"
            value = vehicle.fuelTypeName ?: "---"
        }

        binding.vinDetails.apply {
            title = "Vin"
            value = vehicle.vin ?: "---"
        }

        binding.plateDetails.apply {
            title = "License Plate"
            value = vehicle.licensePlate ?: "---"
        }

        binding.yearDetails.apply {
            title = "Year"
            value = vehicle.year?.toString() ?: "---"
        }

        binding.makeDetails.apply {
            title = "Make"
            value = vehicle.make ?: "---"
        }

        binding.modelDetails.apply {
            title = "Model"
            value = vehicle.model ?: "---"
        }

        binding.trimDetails.apply {
            title = "Trim"
            value = vehicle.trim ?: "---"
        }

        binding.registrationDetails.apply {
            title = "Registration State/Province"
            value = vehicle.registrationState ?: "---"
        }

        binding.colorDetails.apply {
            title = "Color"
            value = vehicle.color ?: "---"
        }

        binding.ownershipDetails.apply {
            title = "Ownership"
            value = vehicle.ownership ?: "---"
        }

        binding.bodyTypeDetails.apply {
            title = "Body Type"
            value = vehicle.specs.bodyType ?: "---"
        }

        binding.bodySubtypeDetails.apply {
            title = "Body Subtype"
            value = vehicle.specs.driveType ?: "---"
        }

        binding.msprDetails.apply {
            title = "MSRP"
            value = vehicle.specs.msrp ?: "---"
            divider.visibility = View.GONE
        }
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