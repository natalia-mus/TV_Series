package com.example.tvseries.presenter

import com.example.tvseries.API.RepositoryCallback
import com.example.tvseries.contracts.MainActivityContract
import com.example.tvseries.datamodel.TVSeries
import com.example.tvseries.model.APIRepository
import javax.inject.Inject

class MainActivityPresenter @Inject constructor() :
    MainActivityContract.MainActivityPresenter {

    @Inject
    lateinit var model: APIRepository
    private lateinit var view: MainActivityContract.MainActivityView
    private var status = false

    override fun setViewToPresenter(view: MainActivityContract.MainActivityView) {
        this.view = view
        view.initView()
    }

    override fun fetchData() {
        model.fetchAllSeriesFromAPI(object : RepositoryCallback<TVSeries> {
            override fun onError() {
                status = false
                view.updateView()
            }

            override fun onSuccess(data: TVSeries) {
                status = true
                model.setData(data)
                view.updateView()
            }
        })
    }

    override fun returnStatus() = status

    override fun returnData() = model.returnData()
}