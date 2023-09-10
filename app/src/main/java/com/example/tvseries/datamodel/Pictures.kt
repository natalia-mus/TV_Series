package com.example.tvseries.datamodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pictures(val pictures: ArrayList<String>) : Parcelable