package com.example.tvseries.contracts

import com.example.tvseries.database.FavoriteShow

interface FavoriteShowsActivityContract {

    interface FavoriteShowsActivityModel {
        fun delete(show: FavoriteShow)
        fun getAll(): List<FavoriteShow>
    }

    interface FavoriteShowsActivityPresenter {
        fun setViewToPresenter(view: FavoriteShowsActivityView)
        fun initView()
        suspend fun returnData(): List<FavoriteShow>
        fun delete(item: FavoriteShow)
    }

    interface FavoriteShowsActivityView {
        fun initView()
        fun initData()
        fun updateView()
    }
}