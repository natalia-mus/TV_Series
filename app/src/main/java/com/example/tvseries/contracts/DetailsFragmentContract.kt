package com.example.tvseries.contracts

import com.example.tvseries.datamodel.TVShow
import com.example.tvseries.datamodel.TVShowForDatabase

interface DetailsFragmentContract {

    interface DetailsFragmentModel {
        fun deleteShow(show: TVShowForDatabase)
        fun saveShow(show: TVShow)
    }

    interface DetailsFragmentPresenter {
        fun deleteShow(show: TVShowForDatabase)
        fun initView(id: Int)
        suspend fun isShowInFavorites(show: TVShow): Boolean
        fun saveShow(show: TVShow)
        fun setViewToPresenter(view: DetailsFragmentView)
    }

    interface DetailsFragmentView {
        fun initView()
        fun updateView(show: TVShow?)
    }
}