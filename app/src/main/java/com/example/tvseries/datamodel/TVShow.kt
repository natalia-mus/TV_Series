package com.example.tvseries.datamodel

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tvseries.objects.DatabaseObject
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = DatabaseObject.tableName)
@Parcelize
data class TVShow(
    @PrimaryKey
    val id: Int,
    val name: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String?,
    val country: String,
    val network: String,
    val status: String,
    @SerializedName("image_path")
    val image: String,
    val description: String,
    val rating: String
) : Parcelable