package com.example.tvseries.presenter

import com.example.tvseries.BaseApplication
import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.model.RoomRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentPresenter : DetailsFragmentContract.DetailsFragmentPresenter {

    init {
        BaseApplication.baseApplicationComponent.inject(this)
    }

    @Inject
    lateinit var model: RoomRepository

    private lateinit var view: DetailsFragmentContract.DetailsFragmentView

    override fun setViewToPresenter(view: DetailsFragmentContract.DetailsFragmentView) {
        this.view = view
    }

    override fun initView() {
        view.initView()
        view.initData()
    }

    override suspend fun isShowInFavorites(show: SingleShow): Boolean {
        val result = GlobalScope.async {
            model.isShowInFavorites(show)
        }
        return result.await()
    }

    override fun saveShow(show: SingleShow) {
        GlobalScope.launch {
            model.saveShow(show)
        }
    }

    override fun deleteShow(show: SingleShow) {
        GlobalScope.launch {
            model.deleteShow(show)
        }
    }

}