package com.example.tvseries.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tvseries.datamodel.TVShowForDatabase

@TypeConverters(com.example.tvseries.datamodel.TypeConverters::class)
@Database(entities = [TVShowForDatabase::class], version = 12, exportSchema = false)
abstract class FavoriteShowsDatabase : RoomDatabase() {

    abstract fun favoriteShowsDao(): FavoriteShowsDao
}