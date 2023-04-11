package com.example.tvseries.contracts

import com.example.tvseries.datamodel.SingleShow

interface DetailsFragmentContract {

    interface DetailsFragmentModel {
        fun saveShow(show: SingleShow)
        fun deleteShow(show: SingleShow)
    }

    interface DetailsFragmentPresenter {
        fun setViewToPresenter(view: DetailsFragmentView)
        fun initView()
        suspend fun isShowInFavorites(show: SingleShow): Boolean
        fun saveShow(show: SingleShow)
        fun deleteShow(show: SingleShow)
    }

    interface DetailsFragmentView {
        fun initView()
        fun initData()
    }
}