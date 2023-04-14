package com.example.onlinecinema.data.remote

import com.example.onlinecinema.core.network.TypedResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("1/search/movies")
    suspend fun searchMovies(
        @Query("page") page: Int,
        @Query("query") query: String,
    ) : TypedResult<SearchMovieResponse>

    @GET("1/movie/{id}")
    suspend fun loadMovieInfo(@Path("id") movieId: Int) : TypedResult<MovieInfoResponse>

}