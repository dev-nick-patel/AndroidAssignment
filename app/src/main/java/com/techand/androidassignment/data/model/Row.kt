package com.techand.androidassignment.data.model

data class Row(
    val description: String,
    val imageHref: String,
    val title: String
) {
    fun noData(): Boolean {
        return description.isNullOrEmpty() && imageHref.isNullOrEmpty() && title.isNullOrEmpty()
    }
}