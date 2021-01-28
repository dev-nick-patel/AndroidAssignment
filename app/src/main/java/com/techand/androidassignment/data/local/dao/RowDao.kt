package com.techand.androidassignment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.techand.androidassignment.data.local.entities.Row

@Dao
interface RowDao {

    @Query("SELECT * FROM rows")
    fun getRows() : LiveData<List<Row>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(row: List<Row>)

    @Query("DELETE from rows")
    suspend fun deleteAll()

}