package com.techand.androidassignment.data.repository

import com.techand.androidassignment.data.model.RowResult
import com.techand.androidassignment.util.Resource

interface MainRepository {

    suspend fun getData() : Resource<RowResult>
}