package com.ch2ps126.capstoneproject.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ch2ps126.capstoneproject.data.remote.repository.EquipmentRepository
import com.ch2ps126.capstoneproject.data.remote.response.EquipmentResponse
import com.ch2ps126.capstoneproject.data.remote.response.EquipmentResponseItem

class HomeViewModel(private val repository: EquipmentRepository)  : ViewModel()  {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _equipmentData = MutableLiveData<List<EquipmentResponseItem>>()
    val equipmentData: LiveData<List<EquipmentResponseItem>> = _equipmentData

    suspend fun getAllEquipment() {
        _isLoading.value = true
        try {
            val data = repository.getAllEquipment()
            _equipmentData.value = data
            _isLoading.value = false
        } catch (e: Exception) {
            Log.d("StoryListViewModel", "Error: ${e.message}")
            _isLoading.value = false
            throw e
        }
    }
}