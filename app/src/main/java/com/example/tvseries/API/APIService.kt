package com.example.tvseries.API

import com.example.tvseries.datamodel.TVSeries
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("most-popular")
    fun fetchAllSeries(): Call<TVSeries>
}