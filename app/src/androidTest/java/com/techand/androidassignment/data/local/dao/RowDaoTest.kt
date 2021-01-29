package com.techand.androidassignment.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.techand.androidassignment.data.local.AppDatabase
import com.techand.androidassignment.data.local.RowDao
import com.techand.androidassignment.data.local.entities.Row
import com.techand.androidassignment.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RowDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: RowDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.rowsDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertRow() = runBlocking {

        val rowList = ArrayList<Row>()

        val row1 = Row(
            id = 1,
            "testDescription1",
        "https://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
            "testTitle1"
        )

        val row2 = Row(
            id = 2,
            "testDescription2",
            "https://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
            "testTitle2"
        )

        rowList.add(row1)
        rowList.add(row2)

        dao.insertAll(rowList)

        val allRows = dao.getRows().getOrAwaitValue()

        assertThat(allRows).contains(row1)
        assertThat(allRows).contains(row2)
        assertThat(allRows).hasSize(2)

    }

    @Test
    fun deleteAllRows() = runBlockingTest {

        val rowList = ArrayList<Row>()

        val row1 = Row(
            id = 1,
            "testDescription1",
            "https://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
            "testTitle1"
        )

        val row2 = Row(
            id = 2,
            "testDescription2",
            "https://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
            "testTitle2"
        )

        rowList.add(row1)
        rowList.add(row2)

        dao.insertAll(rowList)
        dao.deleteAll()

        val allRows = dao.getRows().getOrAwaitValue()
        assertThat(allRows).isEmpty()
    }

    @Test
    fun getRows() = runBlocking {

        val rowList = ArrayList<Row>()

        val row1 = Row(
            id = 1,
            "testDescription1",
            "https://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png",
            "testTitle1"
        )

        rowList.add(row1)

        dao.insertAll(rowList)

        val allRows = dao.getRows().getOrAwaitValue()

        assertThat(allRows).contains(row1)
        assertThat(allRows).isNotEmpty()

    }
}