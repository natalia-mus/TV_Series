package com.example.tvseries.presenter

import com.example.tvseries.API.RepositoryCallback
import com.example.tvseries.BaseApplication
import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.datamodel.TVShow
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.model.APIRepository
import com.example.tvseries.model.RoomRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentPresenter : DetailsFragmentContract.DetailsFragmentPresenter {

    init {
        BaseApplication.baseApplicationComponent.inject(this)
    }

    @Inject
    lateinit var database: RoomRepository

    @Inject
    lateinit var model: APIRepository

    private lateinit var view: DetailsFragmentContract.DetailsFragmentView

    override fun setViewToPresenter(view: DetailsFragmentContract.DetailsFragmentView) {
        this.view = view
    }

    override fun initView(id: Int) {
        view.initView()
        model.getSingleShow(id, object : RepositoryCallback<SingleShow> {
            override fun onError() {
                // todo
            }

            override fun onSuccess(data: SingleShow) {
                // todo
            }
        })
        //view.initData(show)
    }

    override suspend fun isShowInFavorites(show: TVShow): Boolean {
        val result = GlobalScope.async {
            database.isShowInFavorites(show)
        }
        return result.await()
    }

    override fun saveShow(show: TVShow) {
        GlobalScope.launch {
            database.saveShow(show)
        }
    }

    override fun deleteShow(show: TVShow) {
        GlobalScope.launch {
            database.deleteShow(show)
        }
    }

}