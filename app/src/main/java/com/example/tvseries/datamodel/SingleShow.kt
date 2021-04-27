package com.example.tvseries.datamodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SingleShow(
    val id: Int,
    val name: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String?,
    val country: String,
    val network: String,
    val status: String,
    @SerializedName("image_thumbnail_path")
    val image: String,
) : Parcelable