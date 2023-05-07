package com.example.onlinecinema.data.remote

import com.example.onlinecinema.core.network.TypedResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movies")
    suspend fun searchMovies(
        @Query("page") page: Int,
        @Query("title") query: String,
    ) : TypedResult<SearchMovieResponse>

    @GET("movies/{id}")
    suspend fun loadMovieInfo(@Path("id") movieId: Int) : TypedResult<MovieInfoResponse>

    @GET("movies/{id}/player")
    suspend fun loadPlayer(@Path("id") movieId: Int) : TypedResult<PlayerResponse>

}