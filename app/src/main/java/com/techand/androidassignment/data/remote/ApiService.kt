package com.techand.androidassignment.data.remote

import com.techand.androidassignment.data.model.RowResult
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getInfo() : Response<RowResult>
}