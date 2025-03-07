package com.example.vehiclemanagement.vehiclelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclemanagement.R
import com.example.vehiclemanagement.network.models.Vehicle
import com.example.vehiclemanagement.ui.ItemClickListener
import com.example.vehiclemanagement.ui.PaginationScrollListener
import com.example.vehiclemanagement.ui.VehicleAdapter

class VehicleListFragment : Fragment(), ItemClickListener {
    private lateinit var recordRecyclerView: RecyclerView

    private val viewModel: VehicleListViewModel by viewModels()
    private val adapter: VehicleAdapter = VehicleAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_vehicle_list, container, false)

        recordRecyclerView = v.findViewById(R.id.record_list)
        recordRecyclerView.adapter = adapter
        recordRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recordRecyclerView.addOnScrollListener(object :
            PaginationScrollListener(recordRecyclerView.layoutManager as LinearLayoutManager) {
            override val isLastPage: Boolean
                get() = viewModel.isLastPage()

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

            override fun loadMore() {
                viewModel.fetchRecords()
            }
        })

        observeViewModel()
        viewModel.fetchRecords()

        return v.rootView
    }

    private fun observeViewModel() {
        viewModel.recordsList.observe(viewLifecycleOwner) {
            updateRecyclerView(it.records)
        }
    }

    private fun updateRecyclerView(records: List<Vehicle>) {
        adapter.addToDataSet(records)
    }

    override fun onVehicleClicked(recordId: Int) {
        val directions =
            VehicleListFragmentDirections.actionVehicleListFragmentToDetailsFragment2(recordId)
        findNavController().navigate(directions)
    }
}