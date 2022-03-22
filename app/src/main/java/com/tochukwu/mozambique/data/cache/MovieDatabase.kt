package com.tochukwu.mozambique.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tochukwu.mozambique.data.cache.dto.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 4
)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun moviesDao(): MovieDao
}
