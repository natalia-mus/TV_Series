package com.example.tvseries.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteShow::class], version = 3, exportSchema = false)
abstract class FavoriteShowsDatabase : RoomDatabase() {

    abstract fun favoriteShowsDao(): FavoriteShowsDao
}