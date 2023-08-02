package com.example.tvseries.datamodel

import com.google.gson.annotations.SerializedName

data class TVSeries(
    val total: String,
    val page: Int,
    val pages: Int,
    @SerializedName("tv_shows")
    val tvShows: List<SingleShowListItem>
)