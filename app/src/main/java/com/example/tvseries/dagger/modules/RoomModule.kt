package com.example.tvseries.dagger.modules

import android.content.Context
import androidx.room.Room
import com.example.tvseries.database.FavoriteShowsDatabase
import com.example.tvseries.objects.DatabaseObject
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideRoomDatabase(): FavoriteShowsDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteShowsDatabase::class.java,
            DatabaseObject.tableName
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}