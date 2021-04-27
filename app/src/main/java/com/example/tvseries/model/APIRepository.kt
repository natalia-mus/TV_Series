package com.example.tvseries.model

import com.example.tvseries.API.APIService
import com.example.tvseries.API.RepositoryCallback
import com.example.tvseries.contracts.MainActivityContract
import com.example.tvseries.datamodel.TVSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

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
                    callback.onSuccess(response.body()!!)
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