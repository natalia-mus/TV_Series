package com.example.tvseries.presenter

import com.example.tvseries.BaseApplication
import com.example.tvseries.contracts.FavoriteShowsActivityContract
import com.example.tvseries.datamodel.TVShowForDatabase
import com.example.tvseries.model.RoomRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteShowsActivityPresenter :
    FavoriteShowsActivityContract.FavoriteShowsActivityPresenter {

    private lateinit var view: FavoriteShowsActivityContract.FavoriteShowsActivityView

    @Inject
    lateinit var model: RoomRepository

    init {
        BaseApplication.baseApplicationComponent.inject(this)
    }

    override fun setViewToPresenter(view: FavoriteShowsActivityContract.FavoriteShowsActivityView) {
        this.view = view
    }

    override fun initView() {
        view.initView()

        GlobalScope.launch {
            view.initData()
        }
    }

    override suspend fun returnData(): List<TVShowForDatabase> {
        val data = GlobalScope.async {
            model.getFavoriteShows()
        }
        return data.await()
    }

    override fun deleteShow(show: TVShowForDatabase) {
        GlobalScope.launch {
            model.deleteShow(show)
            view.updateView()
        }
    }
}