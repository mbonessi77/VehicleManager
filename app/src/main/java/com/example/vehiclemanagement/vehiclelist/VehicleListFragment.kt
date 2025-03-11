package com.example.vehiclemanagement.vehiclelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclemanagement.R
import com.example.vehiclemanagement.databinding.FragmentVehicleListBinding
import com.example.vehiclemanagement.helpers.PREF_KEY_BUNDLE_DATA
import com.example.vehiclemanagement.helpers.PREF_KEY_FRAG_RESULT
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

        setFragmentResultListener(PREF_KEY_FRAG_RESULT) { _, bundle ->
            val result = bundle.getBoolean(PREF_KEY_BUNDLE_DATA)
            if (result) {
                viewModel.resetData()
                adapter.resetData()
                viewModel.fetchRecords()
            }
        }

        activity?.title = "Vehicles"

        activity?.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.clear_filter -> {
                        viewModel.resetParamMap()
                        viewModel.resetData()
                        adapter.resetData()
                        viewModel.fetchRecords()
                    }

                    else -> {
                        activity?.onBackPressedDispatcher?.onBackPressed()
                    }
                }

                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.fab.setOnClickListener {
            val directions =
                VehicleListFragmentDirections.actionVehicleListFragmentToFilterFragment()
            findNavController().navigate(directions)
        }

        initRecyclerView()
        observeViewModel()
        if (viewModel.isStartUpCall) {
            viewModel.fetchRecords()
        }

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