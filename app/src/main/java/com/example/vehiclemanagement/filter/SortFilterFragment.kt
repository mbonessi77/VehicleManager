package com.example.vehiclemanagement.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import com.example.vehiclemanagement.databinding.FragmentSortFilterBinding
import com.example.vehiclemanagement.helpers.DEFAULT_DROPDOWN_SELECTION
import com.example.vehiclemanagement.helpers.FILTER_TYPE_LABEL
import com.example.vehiclemanagement.helpers.FILTER_TYPE_PLATE
import com.example.vehiclemanagement.helpers.FILTER_TYPE_VIN
import com.example.vehiclemanagement.helpers.PREF_KEY_BUNDLE_DATA
import com.example.vehiclemanagement.helpers.PREF_KEY_FRAG_RESULT
import com.example.vehiclemanagement.helpers.SORT_TYPE_CREATED_AT
import com.example.vehiclemanagement.helpers.SORT_TYPE_NAME
import com.example.vehiclemanagement.helpers.SORT_TYPE_UPDATED_AT
import com.example.vehiclemanagement.vehiclelist.VehicleListViewModel

class SortFilterFragment : Fragment() {
    private val viewModel: VehicleListViewModel by activityViewModels()

    private lateinit var binding: FragmentSortFilterBinding
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortFilterBinding.inflate(inflater)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1)
        binding.labelOptions.setAdapter(adapter)
        observeViewModel()

        setInitialValues()
        initButtonCheckedListeners()
        initTextChangeListeners()

        viewModel.fetchLabels()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.labelsList.observe(viewLifecycleOwner) {
            it.contentIfNotHandled()?.let { records ->
                updateSpinner(records)
            }
        }
    }

    private fun updateSpinner(list: List<String>) {
        adapter.clear()
        adapter.addAll(list)
    }

    private fun setInitialValues() {
        binding.nameOptionButton.isChecked = viewModel.getSortValue(SORT_TYPE_NAME)
        binding.createdAtOptionButton.isChecked = viewModel.getSortValue(SORT_TYPE_CREATED_AT)
        binding.updatedAtOptionButton.isChecked = viewModel.getSortValue(SORT_TYPE_UPDATED_AT)

        binding.nameInputField.setText(viewModel.getFilterValue(SORT_TYPE_NAME))
        binding.vinInputField.setText(viewModel.getFilterValue(FILTER_TYPE_PLATE))
        binding.plateInputField.setText(viewModel.getFilterValue(FILTER_TYPE_VIN))

        binding.labelOptions.setText(viewModel.getDropdownValue(FILTER_TYPE_LABEL), false)
    }

    private fun initButtonCheckedListeners() {
        binding.nameOptionButton.setOnCheckedChangeListener { _, selected ->
            viewModel.setSortValue(selected, SORT_TYPE_NAME)
        }

        binding.createdAtOptionButton.setOnCheckedChangeListener { _, selected ->
            viewModel.setSortValue(selected, SORT_TYPE_CREATED_AT)
        }

        binding.updatedAtOptionButton.setOnCheckedChangeListener { _, selected ->
            viewModel.setSortValue(selected, SORT_TYPE_UPDATED_AT)
        }

        binding.btnSubmit.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
            setFragmentResult(
                PREF_KEY_FRAG_RESULT,
                bundleOf(PREF_KEY_BUNDLE_DATA to viewModel.isFilterChanged)
            )
        }
    }

    private fun initTextChangeListeners() {
        binding.nameInputField.doAfterTextChanged { text ->
            viewModel.setFilterValue(text.toString(), SORT_TYPE_NAME)
        }

        binding.plateInputField.doAfterTextChanged { text ->
            viewModel.setFilterValue(text.toString(), FILTER_TYPE_PLATE)
        }

        binding.vinInputField.doAfterTextChanged { text ->
            viewModel.setFilterValue(text.toString(), FILTER_TYPE_VIN)
        }

        binding.labelOptions.addTextChangedListener {
            if (it.toString() == DEFAULT_DROPDOWN_SELECTION || it.toString() == "null") {
                binding.labelOptions.clearListSelection()
                binding.labelOptions.clearFocus()
                binding.labelOptions.text = null
                viewModel.removeFilter(FILTER_TYPE_LABEL)
            } else {
                viewModel.setDropdownValue(it.toString(), FILTER_TYPE_LABEL)
            }
        }
    }
}