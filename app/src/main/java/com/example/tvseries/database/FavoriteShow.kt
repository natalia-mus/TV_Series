package com.example.tvseries.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tvseries.objects.DatabaseObject

@Entity(tableName = DatabaseObject.tableName)
data class FavoriteShow(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String
)