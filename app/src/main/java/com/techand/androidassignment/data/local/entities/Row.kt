package com.techand.androidassignment.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rows", indices = arrayOf(Index(value = ["title"], unique = true)))
data class Row(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String?,
    val imageHref: String?,
    var title: String?
) {

    fun noData(): Boolean {
        return description.isNullOrEmpty() && imageHref.isNullOrEmpty() && title.isNullOrEmpty()
    }

}
