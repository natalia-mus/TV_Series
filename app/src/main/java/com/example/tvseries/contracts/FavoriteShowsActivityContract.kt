package com.example.tvseries.contracts

import com.example.tvseries.datamodel.TVShow

interface FavoriteShowsActivityContract {

    interface FavoriteShowsActivityModel {
        fun deleteShow(show: TVShow)
        fun getFavoriteShows(): List<TVShow>
        fun isShowInFavorites(show: TVShow): Boolean
    }

    interface FavoriteShowsActivityPresenter {
        fun setViewToPresenter(view: FavoriteShowsActivityView)
        fun initView()
        suspend fun returnData(): List<TVShow>
        fun deleteShow(show: TVShow)
    }

    interface FavoriteShowsActivityView {
        fun initView()
        fun initData()
        fun updateView()
    }
}