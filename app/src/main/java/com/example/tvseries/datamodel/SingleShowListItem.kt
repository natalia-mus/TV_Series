package com.example.tvseries.datamodel

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SingleShowListItem(
    @PrimaryKey
    val id: Int,
    @SerializedName("image_thumbnail_path")
    val image: String,
    val name: String
)
