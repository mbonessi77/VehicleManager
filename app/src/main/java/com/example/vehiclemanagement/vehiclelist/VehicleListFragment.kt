package com.example.vehiclemanagement.vehiclelist

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.vehiclemanagement.R

class VehicleListFragment : Fragment() {
    private lateinit var detailsButton: Button

    private val viewModel: VehicleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchRecords()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_vehicle_list, container, false)

        detailsButton = v.findViewById(R.id.btn_details)
        detailsButton.setOnClickListener {
            findNavController().navigate(R.id.detailsFragment)
        }

        observeViewModel()

        return v.rootView
    }

    private fun observeViewModel() {
        viewModel.recordsList.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}