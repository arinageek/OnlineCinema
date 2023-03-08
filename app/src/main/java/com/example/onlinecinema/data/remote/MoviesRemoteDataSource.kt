package com.example.onlinecinema.data.remote

import com.example.onlinecinema.core.network.TypedResult
import javax.inject.Inject

interface MoviesRemoteDataSource {

    suspend fun searchMovies(page: Int, query: String): TypedResult<SearchMovieResponse>
}

class MoviesRemoteDataSourceImpl @Inject constructor(
    private val api: MovieApi,
): MoviesRemoteDataSource {

    override suspend fun searchMovies(page: Int, query: String): TypedResult<SearchMovieResponse> {
        return api.searchMovies(page, query)
    }
}