package com.example.tvseries.contracts

import com.example.tvseries.datamodel.SingleShow

interface FavoriteShowsActivityContract {

    interface FavoriteShowsActivityModel {
        fun deleteShow(show: SingleShow)
        fun getFavoriteShows(): List<SingleShow>
        fun isShowInFavorites(show: SingleShow): Boolean
    }

    interface FavoriteShowsActivityPresenter {
        fun setViewToPresenter(view: FavoriteShowsActivityView)
        fun initView()
        suspend fun returnData(): List<SingleShow>
        fun deleteShow(show: SingleShow)
    }

    interface FavoriteShowsActivityView {
        fun initView()
        fun initData()
        fun updateView()
    }
}