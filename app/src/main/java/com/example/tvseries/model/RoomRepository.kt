package com.example.tvseries.model

import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.contracts.FavoriteShowsActivityContract
import com.example.tvseries.database.FavoriteShowsDatabase
import com.example.tvseries.datamodel.SingleShow
import javax.inject.Inject

class RoomRepository @Inject constructor() : DetailsFragmentContract.DetailsFragmentModel,
    FavoriteShowsActivityContract.FavoriteShowsActivityModel {

    @Inject
    lateinit var roomDatabase: FavoriteShowsDatabase

    override fun saveShow(show: SingleShow) {
        roomDatabase.favoriteShowsDao().insert(show)
    }


    override fun deleteShow(show: SingleShow) {
        roomDatabase.favoriteShowsDao().delete(show)
    }

    override fun getFavoriteShows() = roomDatabase.favoriteShowsDao().getAll()

    override fun isShowInFavorites(show: SingleShow): Boolean {
        val favorites = getFavoriteShows()
        return favorites.contains(show)
    }

}