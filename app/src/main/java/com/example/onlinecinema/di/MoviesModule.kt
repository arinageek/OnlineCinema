package com.example.onlinecinema.di

import com.example.onlinecinema.data.MoviesRepository
import com.example.onlinecinema.data.MoviesRepositoryImpl
import com.example.onlinecinema.data.remote.MoviesRemoteDataSource
import com.example.onlinecinema.data.remote.MoviesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MoviesModule {

    @Binds
    fun bindMoviesRemoteDataSource(
        dataSource: MoviesRemoteDataSourceImpl,
    ): MoviesRemoteDataSource

    @Binds
    fun bindMoviesRepository(
        repositoryImpl: MoviesRepositoryImpl,
    ): MoviesRepository

}