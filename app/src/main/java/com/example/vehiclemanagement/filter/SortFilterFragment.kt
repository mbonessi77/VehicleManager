package com.example.vehiclemanagement.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.example.vehiclemanagement.databinding.FragmentSortFilterBinding
import com.example.vehiclemanagement.vehiclelist.VehicleListViewModel

class SortFilterFragment : Fragment() {
    private val viewModel: VehicleListViewModel by activityViewModels()

    private lateinit var binding: FragmentSortFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortFilterBinding.inflate(inflater)
        binding.nameOptionButton.setOnCheckedChangeListener { _, selected ->
            viewModel.setSortValue(selected, "name")
        }

        binding.createdAtOptionButton.setOnCheckedChangeListener { _, selected ->
            viewModel.setSortValue(selected, "created_at")
        }

        binding.updatedAtOptionButton.setOnCheckedChangeListener { _, selected ->
            viewModel.setSortValue(selected, "updated_at")
        }

        binding.nameInputField.doAfterTextChanged { text ->
            viewModel.setFilterValue(text.toString(), "name")
        }

        binding.plateInputField.doAfterTextChanged { text ->
            viewModel.setFilterValue(text.toString(), "license_plate")
        }

        binding.vinInputField.doAfterTextChanged { text ->
            viewModel.setFilterValue(text.toString(), "vin")
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.resetData()
            requireActivity().onBackPressedDispatcher.onBackPressed()
            setFragmentResult("KEY", bundleOf("data" to viewModel.isFilterChanged))
        }
        return binding.root
    }
}