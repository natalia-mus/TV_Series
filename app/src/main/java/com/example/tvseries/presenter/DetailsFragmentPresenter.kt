package com.example.tvseries.presenter

import android.util.Log
import com.example.tvseries.BaseApplication
import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.database.FavoriteShow
import com.example.tvseries.model.RoomRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentPresenter :
    DetailsFragmentContract.DetailsFragmentPresenter {

    init {
        BaseApplication.baseApplicationComponent.inject(this)
    }

    private lateinit var view: DetailsFragmentContract.DetailsFragmentView

    @Inject
    lateinit var model: RoomRepository

    override fun setViewToPresenter(view: DetailsFragmentContract.DetailsFragmentView) {
        this.view = view
        Log.e("model", model.toString())
    }

    override fun initView() {
        view.initView()
        view.initData()
    }

    override fun saveShow(show: FavoriteShow) {
        GlobalScope.launch {
            model.save(show)
        }
    }

}