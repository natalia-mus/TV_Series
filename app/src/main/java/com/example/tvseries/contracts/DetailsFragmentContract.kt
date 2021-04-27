package com.example.tvseries.contracts

import com.example.tvseries.database.FavoriteShow

interface DetailsFragmentContract {

    interface DetailsFragmentModel {
        fun save(show: FavoriteShow)
    }

    interface DetailsFragmentPresenter {
        fun setViewToPresenter(view: DetailsFragmentView)
        fun initView()
        fun saveShow(show: FavoriteShow)
    }

    interface DetailsFragmentView {
        fun initView()
        fun initData()
    }
}