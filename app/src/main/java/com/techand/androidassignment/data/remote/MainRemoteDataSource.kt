package com.techand.androidassignment.data.remote

import javax.inject.Inject

class MainRemoteDataSource @Inject constructor(
    private val apiService: ApiService
): BaseDataSource() {

    suspend fun getData() = getResult {
        apiService.getData()
    }
}