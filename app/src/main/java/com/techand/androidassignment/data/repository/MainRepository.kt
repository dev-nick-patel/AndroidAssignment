package com.techand.androidassignment.data.repository

import com.techand.androidassignment.data.local.dao.RowDao
import com.techand.androidassignment.data.local.entities.RowResult
import com.techand.androidassignment.data.remote.ApiService
import com.techand.androidassignment.data.remote.MainRemoteDataSource
import com.techand.androidassignment.util.Constants.Companion.titleBar
import com.techand.androidassignment.util.Resource
import com.techand.androidassignment.util.performGetOperation
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: MainRemoteDataSource,
    private val localDataSource: RowDao
) {
    fun getData() = performGetOperation(
        databaseQuery = { localDataSource.getRows() },
        networkCall = { remoteDataSource.getData() },
        saveCallResult = { it ->
            titleBar = it.title
            localDataSource.deleteAll()
            localDataSource.insertAll(it.rows)
        }
    )

}