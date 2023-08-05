package com.example.tvseries.API

import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.datamodel.TVSeries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("most-popular")
    fun fetchAllSeries(): Call<TVSeries>

    @GET("search")
    fun fetchMatchingSeries(@Query("q") phrase: String): Call<TVSeries>

    @GET("show-details")
    fun getSingleShow(@Query("q") id: Int): Call<SingleShow>

}