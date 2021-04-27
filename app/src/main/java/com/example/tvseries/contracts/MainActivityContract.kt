package com.example.tvseries.contracts

import com.example.tvseries.API.RepositoryCallback
import com.example.tvseries.datamodel.TVSeries

interface MainActivityContract {

    interface MainActivityModel {
        fun fetchAllSeriesFromAPI(callback: RepositoryCallback<TVSeries>)
        fun setData(data: TVSeries)
        fun returnData(): TVSeries
    }

    interface MainActivityPresenter {
        fun setViewToPresenter(view: MainActivityView)
        fun fetchData()
        fun returnStatus(): Boolean
        fun returnData(): TVSeries
    }

    interface MainActivityView {
        fun initView()
        fun updateView()
    }
}