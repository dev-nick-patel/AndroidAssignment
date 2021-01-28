package com.techand.androidassignment.data.repository

import com.techand.androidassignment.data.local.entities.RowResult
import com.techand.androidassignment.data.remote.ApiService
import com.techand.androidassignment.util.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val apiService: ApiService
) : MainRepository{

    override suspend fun getData(): Resource<RowResult> {
        return try {
            val response = apiService.getInfo()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else Resource.error("An unknown error occured", null)

        } catch (ex: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }

    }


}