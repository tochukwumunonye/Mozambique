package com.tochukwu.mozambique.di

import android.content.Context
import androidx.room.Room
import com.tochukwu.mozambique.data.cache.Converter
import com.tochukwu.mozambique.data.cache.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) = Room
            .databaseBuilder(context, MovieDatabase::class.java, "db").fallbackToDestructiveMigration().build()

        @Singleton
        @Provides
        fun provideMovieDao(db: MovieDatabase) = db.moviesDao()

    }

/**



@Singleton
@Provides
fun provideMovieDatabase(@ApplicationContext context: Context) = Room
.databaseBuilder(context, MovieDatabase::class.java, "db").addTypeConverter(Converter()).build()
 **/