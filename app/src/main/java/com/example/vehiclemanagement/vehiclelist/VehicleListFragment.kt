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
import com.example.vehiclemanagement.network.models.Record
import com.example.vehiclemanagement.ui.ItemClickListener
import com.example.vehiclemanagement.ui.VehicleAdapter

class VehicleListFragment : Fragment(), ItemClickListener {
    private lateinit var recordRecyclerView: RecyclerView

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

        recordRecyclerView = v.findViewById(R.id.record_list)
        recordRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel()

        return v.rootView
    }

    private fun observeViewModel() {
        viewModel.recordsList.observe(viewLifecycleOwner) {
            updateRecyclerView(it.records)
        }
    }

    private fun updateRecyclerView(records: List<Record>) {
        recordRecyclerView.adapter = VehicleAdapter(records, this)
    }

    override fun onVehicleClicked(recordId: Int) {
        val directions =
            VehicleListFragmentDirections.actionVehicleListFragmentToDetailsFragment2(recordId)
        findNavController().navigate(directions)
    }
}