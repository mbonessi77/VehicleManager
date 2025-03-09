package com.example.vehiclemanagement.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclemanagement.helpers.Event
import com.example.vehiclemanagement.network.VehiclesRepository
import com.example.vehiclemanagement.network.models.RecordListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleListViewModel : ViewModel() {
    private val repository: VehiclesRepository = VehiclesRepository()

    private val _recordsList = MutableLiveData<Event<RecordListResponse?>>()
    val recordsList: LiveData<Event<RecordListResponse?>> = _recordsList

    private var nextCursor: String? = null
    var isLoading: Boolean = false
    var isFilterChanged: Boolean = false

    private var map: HashMap<String, String> = HashMap()

    fun setSortValue(selected: Boolean, fieldName: String) {
        isFilterChanged = true
        map["sort[$fieldName]"] = if (selected) "asc" else "desc"
    }

    fun setFilterValue(filterValue: String, filterName: String) {
        isFilterChanged = true
        map["filter[$filterName][like]"] = filterValue
    }

    fun resetData() {
        nextCursor = null
        isFilterChanged = false
    }

    fun fetchRecords() {
        if (!isLoading) {
            isFilterChanged = false
            isLoading = true
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.getRecordsList(
                    cursor = nextCursor,
                    map
                )

                nextCursor = response.body()?.nextCursor

                _recordsList.postValue(Event(response.body()))
                isLoading = false
            }
        }
    }

    fun isLastPage(): Boolean {
        return nextCursor.isNullOrEmpty()
    }
}