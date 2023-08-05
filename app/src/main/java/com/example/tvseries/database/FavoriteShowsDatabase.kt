package com.example.tvseries.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tvseries.datamodel.TVShow

@Database(entities = [TVShow::class], version = 6, exportSchema = false)
abstract class FavoriteShowsDatabase : RoomDatabase() {

    abstract fun favoriteShowsDao(): FavoriteShowsDao
}