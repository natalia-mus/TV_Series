package com.example.tvseries.contracts

import com.example.tvseries.database.FavoriteShow

interface FavoriteShowsActivityContract {

    interface FavoriteShowsActivityModel {
        fun deleteShow(show: FavoriteShow)
        fun getFavoriteShows(): List<FavoriteShow>
        fun isShowInFavorites(show: FavoriteShow): Boolean
    }

    interface FavoriteShowsActivityPresenter {
        fun setViewToPresenter(view: FavoriteShowsActivityView)
        fun initView()
        suspend fun returnData(): List<FavoriteShow>
        fun deleteShow(show: FavoriteShow)
    }

    interface FavoriteShowsActivityView {
        fun initView()
        fun initData()
        fun updateView()
    }
}