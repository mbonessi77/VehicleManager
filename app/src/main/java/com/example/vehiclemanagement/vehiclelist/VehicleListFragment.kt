package com.example.vehiclemanagement.vehiclelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclemanagement.databinding.FragmentVehicleListBinding
import com.example.vehiclemanagement.network.models.Vehicle
import com.example.vehiclemanagement.ui.ItemClickListener
import com.example.vehiclemanagement.ui.PaginationScrollListener
import com.example.vehiclemanagement.ui.VehicleAdapter

class VehicleListFragment : Fragment(), ItemClickListener {
    private val viewModel: VehicleListViewModel by activityViewModels()
    private val adapter: VehicleAdapter = VehicleAdapter(this)
    private lateinit var binding: FragmentVehicleListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleListBinding.inflate(inflater)

        setFragmentResultListener("KEY") { _, bundle ->
            val result = bundle.getBoolean("data")
            if (result) {
                viewModel.resetData()
                adapter.resetData()
                viewModel.fetchRecords()
            }
        }

        binding.fab.setOnClickListener {
            viewModel.resetData()
            adapter.resetData()
            val directions =
                VehicleListFragmentDirections.actionVehicleListFragmentToFilterFragment()
            findNavController().navigate(directions)
        }

        initRecyclerView()
        observeViewModel()
        viewModel.fetchRecords()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recordList.adapter = adapter
        binding.recordList.layoutManager = LinearLayoutManager(requireContext())
        binding.recordList.addOnScrollListener(object :
            PaginationScrollListener(binding.recordList.layoutManager as LinearLayoutManager) {
            override val isLastPage: Boolean
                get() = viewModel.isLastPage()

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMore() {
                viewModel.fetchRecords()
            }
        })
    }

    private fun observeViewModel() {
        viewModel.recordsList.observe(viewLifecycleOwner) {
            updateRecyclerView(it?.contentIfNotHandled()?.records)
        }
    }

    private fun updateRecyclerView(records: List<Vehicle>?) {
        records?.let { adapter.addToDataSet(records) }
    }

    override fun onVehicleClicked(recordId: Int) {
        val directions =
            VehicleListFragmentDirections.actionVehicleListFragmentToDetailsFragment2(recordId)
        findNavController().navigate(directions)
    }
}