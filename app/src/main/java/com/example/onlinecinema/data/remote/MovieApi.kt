package com.example.onlinecinema.data.remote

import com.example.onlinecinema.core.network.TypedResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("1/search/movies")
    suspend fun searchMovies(
        @Query("page") page: Int,
        @Query("query") query: String,
    ) : TypedResult<SearchMovieResponse>

}