package com.example.vehiclemanagement.vehiclelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclemanagement.helpers.DEFAULT_DROPDOWN_SELECTION
import com.example.vehiclemanagement.helpers.Event
import com.example.vehiclemanagement.helpers.FILTER_HEADER
import com.example.vehiclemanagement.helpers.INCLUDE_HEADER_PARAM
import com.example.vehiclemanagement.helpers.LIKE_HEADER_PARAM
import com.example.vehiclemanagement.helpers.SORT_HEADER
import com.example.vehiclemanagement.helpers.SORT_VAL_ASC
import com.example.vehiclemanagement.helpers.SORT_VAL_DESC
import com.example.vehiclemanagement.network.VehiclesRepository
import com.example.vehiclemanagement.network.models.RecordListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleListViewModel : ViewModel() {
    private val repository: VehiclesRepository = VehiclesRepository()

    private val _recordsList = MutableLiveData<Event<RecordListResponse?>>()
    val recordsList: LiveData<Event<RecordListResponse?>> = _recordsList

    private val _labelsList = MutableLiveData<Event<List<String>>>()
    val labelsList: LiveData<Event<List<String>>> = _labelsList

    private var nextCursor: String? = null
    var isLoading: Boolean = false
    var isFilterChanged: Boolean = false
    var isStartUpCall: Boolean = true

    private var paramMap: HashMap<String, String> = HashMap()
    private var labelNames: MutableList<String> = mutableListOf(DEFAULT_DROPDOWN_SELECTION)

    fun setSortValue(selected: Boolean, fieldName: String) {
        val key = buildHashMapKey(SORT_HEADER, fieldName)
        isFilterChanged = true
        paramMap[key] = getButtonCheckedValue(selected)
    }

    fun getSortValue(fieldName: String): Boolean {
        val key = buildHashMapKey(SORT_HEADER, fieldName)
        return paramMap[key] == SORT_VAL_ASC
    }

    fun getFilterValue(fieldName: String): String? {
        val key = buildHashMapKey(FILTER_HEADER, fieldName, LIKE_HEADER_PARAM)
        return paramMap[key]
    }

    fun removeFilter(fieldName: String) {
        val key = buildHashMapKey(FILTER_HEADER, fieldName, INCLUDE_HEADER_PARAM)
        paramMap.remove(key)
    }

    fun setFilterValue(filterValue: String, filterName: String) {
        val key = buildHashMapKey(FILTER_HEADER, filterName, LIKE_HEADER_PARAM)
        isFilterChanged = paramMap[key] != filterValue
        paramMap[key] = filterValue
    }

    fun setDropdownValue(filterValue: String, filterName: String) {
        val key = buildHashMapKey(FILTER_HEADER, filterName, INCLUDE_HEADER_PARAM)

        isFilterChanged = paramMap[key] != filterValue
        if (isFilterChanged) {
            if (filterValue.isEmpty() || filterValue == DEFAULT_DROPDOWN_SELECTION) {
                paramMap.remove(key)
            } else {
                paramMap[key] = filterValue
            }
        }
    }

    fun getDropdownValue(filterName: String): String? {
        val key = buildHashMapKey(FILTER_HEADER, filterName, INCLUDE_HEADER_PARAM)
        return paramMap[key]
    }

    fun resetData() {
        nextCursor = null
        isFilterChanged = false
    }

    fun fetchRecords() {
        if (!isLoading) {
            isFilterChanged = false
            isLoading = true
            isStartUpCall = false
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.getRecordsList(
                    cursor = nextCursor,
                    paramMap
                )

                nextCursor = response.body()?.nextCursor

                _recordsList.postValue(Event(response.body()))
                isLoading = false
            }
        }
    }

    fun fetchLabels() {
        if (!isLoading) {
            labelNames = mutableListOf(DEFAULT_DROPDOWN_SELECTION)
            isLoading = true
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.getLabels()
                response.body()?.records?.forEach { item ->
                    labelNames.add(item.name)
                }
                _labelsList.postValue(Event(labelNames))
                isLoading = false
            }
        }
    }

    fun isLastPage(): Boolean {
        return nextCursor.isNullOrEmpty()
    }

    private fun getButtonCheckedValue(checked: Boolean): String {
        return if (checked) SORT_VAL_ASC else SORT_VAL_DESC
    }

    private fun buildHashMapKey(
        keyType: String,
        keyValue: String,
        strictness: String? = null
    ): String {
        val key = "$keyType[$keyValue]"

        return if (!strictness.isNullOrEmpty()) {
            key.plus("[$strictness]")
        } else {
            key
        }
    }
}