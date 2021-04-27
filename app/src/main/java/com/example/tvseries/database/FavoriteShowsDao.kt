package com.example.tvseries.database

import androidx.room.*
import com.example.tvseries.objects.DatabaseObject

@Dao
interface FavoriteShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(show: FavoriteShow)

    @Delete
    fun delete(show: FavoriteShow)

    @Query(DatabaseObject.getAll)
    fun getAll(): List<FavoriteShow>
}