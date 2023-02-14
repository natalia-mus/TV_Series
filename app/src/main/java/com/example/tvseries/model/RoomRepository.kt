package com.example.tvseries.model

import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.contracts.FavoriteShowsActivityContract
import com.example.tvseries.database.FavoriteShow
import com.example.tvseries.database.FavoriteShowsDatabase
import javax.inject.Inject

class RoomRepository @Inject constructor() : DetailsFragmentContract.DetailsFragmentModel,
    FavoriteShowsActivityContract.FavoriteShowsActivityModel {

    @Inject
    lateinit var roomDatabase: FavoriteShowsDatabase

    override fun save(show: FavoriteShow) {
        roomDatabase.favoriteShowsDao().insert(show)
    }


    override fun delete(show: FavoriteShow) {
        roomDatabase.favoriteShowsDao().delete(show)
    }

    override fun getAll() = roomDatabase.favoriteShowsDao().getAll()

}