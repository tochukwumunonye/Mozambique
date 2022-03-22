package com.tochukwu.mozambique.di

import com.tochukwu.mozambique.data.cache.MovieDao
import com.tochukwu.mozambique.data.remote.MovieApi
import com.tochukwu.mozambique.data.repository.MainRepositoryImpl
import com.tochukwu.mozambique.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
    object AppModule {

        @Singleton
        @Provides
        fun provideMovieRepository(
            dao: MovieDao,
            apiService: MovieApi
        ) = MainRepositoryImpl(
            dao = dao,
           api = apiService
        ) as MainRepository


}