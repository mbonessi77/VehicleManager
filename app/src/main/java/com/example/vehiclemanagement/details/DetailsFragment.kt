package com.example.vehiclemanagement.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.vehiclemanagement.R

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        observeViewModel()
        viewModel.getVehicleData(args.recordId)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    private fun observeViewModel() {
        viewModel.vehicleRecord.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }
    }
}