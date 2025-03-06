package com.example.vehiclemanagement.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.vehiclemanagement.databinding.FragmentDetailsBinding

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
        }
    }
}