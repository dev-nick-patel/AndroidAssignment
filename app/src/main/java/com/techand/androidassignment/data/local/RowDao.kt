package com.techand.androidassignment.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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