package com.example.tvseries.model

import com.example.tvseries.API.APIService
import com.example.tvseries.API.RepositoryCallback
import com.example.tvseries.contracts.MainActivityContract
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.datamodel.TVSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// https://www.episodate.com/api/most-popular
// https://www.episodate.com/api/search?q=dance
// https://www.episodate.com/api/show-details?q=29560

class APIRepository @Inject constructor() : MainActivityContract.MainActivityModel {

    @Inject
    lateinit var apiService: APIService

    private lateinit var data: TVSeries

    override fun fetchAllSeriesFromAPI(callback: RepositoryCallback<TVSeries>) {
        apiService.fetchAllSeries().enqueue(object : Callback<TVSeries> {
            override fun onFailure(call: Call<TVSeries>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TVSeries>, response: Response<TVSeries>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                } else {
                    callback.onError()
                }
            }
        })
    }

    override fun fetchMatchingSeriesFromAPI(phrase: String, callback: RepositoryCallback<TVSeries>) {
        apiService.fetchMatchingSeries(phrase).enqueue(object : Callback<TVSeries> {
            override fun onFailure(call: Call<TVSeries>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<TVSeries>, response: Response<TVSeries>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                } else {
                    callback.onError()
                }
            }
        })
    }

    fun getSingleShow(id: Int, callback: RepositoryCallback<SingleShow>) {
        apiService.getSingleShow(id).enqueue(object : Callback<SingleShow> {
            override fun onFailure(call: Call<SingleShow>, t: Throwable) {
                callback.onError()
            }

            override fun onResponse(call: Call<SingleShow>, response: Response<SingleShow>) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                } else {
                    callback.onError()
                }
            }
        })
    }

    override fun setData(data: TVSeries) {
        this.data = data
    }

    override fun returnData() = data

}