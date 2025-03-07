package com.example.vehiclemanagement.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclemanagement.network.VehiclesRepository
import com.example.vehiclemanagement.network.models.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    private val repository = VehiclesRepository()

    private val _vehicleRecord = MutableLiveData<Vehicle>()
    val vehicleRecord: LiveData<Vehicle> = _vehicleRecord

    fun getVehicleData(record: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getVehicleData(record)

            _vehicleRecord.postValue(response.body())
        }
    }
}