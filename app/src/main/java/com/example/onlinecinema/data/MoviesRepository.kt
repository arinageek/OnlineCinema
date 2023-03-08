package com.example.onlinecinema.data

import com.example.onlinecinema.core.network.TypedResult
import com.example.onlinecinema.data.remote.MoviesRemoteDataSource
import com.example.onlinecinema.data.remote.SearchMovieResponse
import com.example.onlinecinema.utils.runOperationCatching
import javax.inject.Inject

interface MoviesRepository {

    suspend fun searchMovies(page: Int, query: String): TypedResult<SearchMovieResponse>
}

class MoviesRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
) : MoviesRepository {

    override suspend fun searchMovies(page: Int, query: String): TypedResult<SearchMovieResponse> {
        return runOperationCatching {
            moviesRemoteDataSource.searchMovies(page, query)
        }
    }
}
