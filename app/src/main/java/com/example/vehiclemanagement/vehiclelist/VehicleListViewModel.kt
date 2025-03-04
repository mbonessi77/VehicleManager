package com.example.vehiclemanagement.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclemanagement.network.VehiclesRepository
import com.example.vehiclemanagement.network.models.RecordListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleListViewModel : ViewModel() {
    private val repository: VehiclesRepository = VehiclesRepository()

    private val _recordsList = MutableLiveData<RecordListResponse>()
    val recordsList: LiveData<RecordListResponse> = _recordsList

    fun fetchRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRecordsList()

            _recordsList.postValue(response.body())
        }
    }
}