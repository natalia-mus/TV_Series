package com.example.tvseries.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tvseries.datamodel.SingleShow

@Database(entities = [SingleShow::class], version = 4, exportSchema = false)
abstract class FavoriteShowsDatabase : RoomDatabase() {

    abstract fun favoriteShowsDao(): FavoriteShowsDao
}