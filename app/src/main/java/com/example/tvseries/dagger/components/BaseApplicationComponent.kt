package com.example.tvseries.dagger.components

import android.app.Application
import com.example.tvseries.dagger.modules.RetrofitModule
import com.example.tvseries.dagger.modules.RoomModule
import com.example.tvseries.presenter.DetailsFragmentPresenter
import com.example.tvseries.presenter.FavoriteShowsActivityPresenter
import com.example.tvseries.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class, RoomModule::class])
interface BaseApplicationComponent {

    fun inject(app: Application)
    fun inject(mainActivity: MainActivity)
    fun inject(detailsFragmentPresenter: DetailsFragmentPresenter)
    fun inject(favoriteShowsActivityPresenter: FavoriteShowsActivityPresenter)
}