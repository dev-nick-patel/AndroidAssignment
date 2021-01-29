package com.techand.androidassignment.data.repository

import com.techand.androidassignment.data.local.RowDao
import com.techand.androidassignment.data.remote.MainRemoteDataSource
import com.techand.androidassignment.util.Constants.Companion.Prefs
import com.techand.androidassignment.util.performGetOperation
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: MainRemoteDataSource,
    private val localDataSource: RowDao
) {
    fun getRowData() = performGetOperation(
        databaseQuery = { localDataSource.getRows() },
        networkCall = { remoteDataSource.getData() },
        saveCallResult = {
            save(it.title)
            localDataSource.insertAll(it.rows)
        }
    )

    private fun save(title: String) {
        Prefs.edit().putString("title", title).apply()
    }
}