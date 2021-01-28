package com.techand.androidassignment.data.remote

import com.techand.androidassignment.data.local.entities.RowResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getData() : Response<RowResult>
}