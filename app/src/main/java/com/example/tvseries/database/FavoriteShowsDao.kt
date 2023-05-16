package com.example.tvseries.database

import androidx.room.*
import com.example.tvseries.datamodel.SingleShow
import com.example.tvseries.objects.DatabaseObject

@Dao
interface FavoriteShowsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(show: SingleShow)

    @Delete
    fun delete(show: SingleShow)

    @Query(DatabaseObject.getAll)
    fun getAll(): List<SingleShow>
}