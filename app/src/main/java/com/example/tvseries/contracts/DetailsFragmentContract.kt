package com.example.tvseries.contracts

import com.example.tvseries.database.FavoriteShow

interface DetailsFragmentContract {

    interface DetailsFragmentModel {
        fun saveShow(show: FavoriteShow)
        fun deleteShow(show: FavoriteShow)
    }

    interface DetailsFragmentPresenter {
        fun setViewToPresenter(view: DetailsFragmentView)
        fun initView()
        suspend fun isShowInFavorites(show: FavoriteShow): Boolean
        fun saveShow(show: FavoriteShow)
        fun deleteShow(show: FavoriteShow)
    }

    interface DetailsFragmentView {
        fun initView()
        fun initData()
    }
}