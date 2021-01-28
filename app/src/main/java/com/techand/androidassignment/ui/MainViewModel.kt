package com.techand.androidassignment.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.techand.androidassignment.data.local.entities.Row
import com.techand.androidassignment.data.local.entities.RowResult
import com.techand.androidassignment.data.repository.MainRepository
import com.techand.androidassignment.util.Resource
import kotlinx.coroutines.Dispatchers
import java.net.UnknownHostException

class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository
) : ViewModel() {

    var items = repository.getData()

    fun getData(): LiveData<Resource<List<Row>>> {
        items = repository.getData()
        return items
    }

//    fun getData() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = repository.getData()))
//        } catch (exception: UnknownHostException) {
//            emit(Resource.error(data = null, message = "No Internet Connection"))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }

}