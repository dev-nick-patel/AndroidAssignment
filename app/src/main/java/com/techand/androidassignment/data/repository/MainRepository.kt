package com.techand.androidassignment.data.repository

import com.techand.androidassignment.data.local.entities.RowResult
import com.techand.androidassignment.util.Resource

interface MainRepository {

    suspend fun getData() : Resource<RowResult>
}