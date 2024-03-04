package com.example.tvseries.datamodel

import com.google.gson.annotations.SerializedName

data class TVSeries(
    @SerializedName("tv_shows")
    val tvShows: List<SingleShowListItem>
)