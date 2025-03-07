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

    private var nextCursor: String? = null
    private var allRecordsLoaded: Boolean = false
    var isLoading: Boolean = false

    fun fetchRecords() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val response = repository.getRecordsList(nextCursor)

            nextCursor = response.body()?.nextCursor

            _recordsList.postValue(response.body())
            isLoading = false
        }
    }

    fun isLastPage(): Boolean {
        return nextCursor.isNullOrEmpty()
    }
}