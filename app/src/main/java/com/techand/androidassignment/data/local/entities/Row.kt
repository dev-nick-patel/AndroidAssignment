package com.techand.androidassignment.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rows")
data class Row(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val description: String?,
    val imageHref: String?,
    val title: String?
) {
    fun noData(): Boolean {
        return description.isNullOrEmpty() && imageHref.isNullOrEmpty() && title.isNullOrEmpty()
    }
}