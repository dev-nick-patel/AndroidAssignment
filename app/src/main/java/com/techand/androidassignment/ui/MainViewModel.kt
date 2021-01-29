package com.techand.androidassignment.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.techand.androidassignment.data.repository.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    var items = repository.getRowData()

    fun refreshData() {
        items = repository.getRowData()
    }

    val empty: LiveData<Boolean> = Transformations.map(items) {
        it.data.isNullOrEmpty()
    }

}