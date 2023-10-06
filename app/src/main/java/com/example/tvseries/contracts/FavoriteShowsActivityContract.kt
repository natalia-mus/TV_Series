package com.example.tvseries.contracts

import com.example.tvseries.datamodel.TVShowForDatabase

interface FavoriteShowsActivityContract {

    interface FavoriteShowsActivityModel {
        fun getFavoriteShows(): List<TVShowForDatabase>
        fun isShowInFavorites(show: TVShowForDatabase): Boolean
    }

    interface FavoriteShowsActivityPresenter {
        fun setViewToPresenter(view: FavoriteShowsActivityView)
        fun initView()
        suspend fun returnData(): List<TVShowForDatabase>
        fun deleteShow(show: TVShowForDatabase)
    }

    interface FavoriteShowsActivityView {
        fun initView()
        fun initData()
        fun updateView()
    }
}