package com.example.tvseries.database

import androidx.room.*
import com.example.tvseries.datamodel.TVShow
import com.example.tvseries.objects.DatabaseObject

@Dao
interface FavoriteShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(show: TVShow)

    @Delete
    fun delete(show: TVShow)

    @Query(DatabaseObject.getAll)
    fun getAll(): List<TVShow>
}