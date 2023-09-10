package com.example.tvseries.database

import androidx.room.*
import com.example.tvseries.datamodel.TVShowForDatabase
import com.example.tvseries.objects.DatabaseObject

@Dao
interface FavoriteShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(show: TVShowForDatabase)

    @Delete
    fun delete(show: TVShowForDatabase)

    @Query(DatabaseObject.getAll)
    fun getAll(): List<TVShowForDatabase>
}