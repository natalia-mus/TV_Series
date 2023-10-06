package com.example.tvseries.model

import com.example.tvseries.contracts.DetailsFragmentContract
import com.example.tvseries.contracts.FavoriteShowsActivityContract
import com.example.tvseries.database.FavoriteShowsDatabase
import com.example.tvseries.datamodel.TVShow
import com.example.tvseries.datamodel.TVShowForDatabase
import javax.inject.Inject

class RoomRepository @Inject constructor() : DetailsFragmentContract.DetailsFragmentModel,
    FavoriteShowsActivityContract.FavoriteShowsActivityModel {

    @Inject
    lateinit var roomDatabase: FavoriteShowsDatabase

    override fun saveShow(show: TVShow) {
        roomDatabase.favoriteShowsDao().insert(TVShowForDatabase(show))
    }

    override fun deleteShow(show: TVShowForDatabase) {
        roomDatabase.favoriteShowsDao().delete(show)
    }

    override fun getFavoriteShows() = roomDatabase.favoriteShowsDao().getAll()

    override fun isShowInFavorites(show: TVShowForDatabase): Boolean {
        val favorites = getFavoriteShows()
        return favorites.contains(show)
    }

}