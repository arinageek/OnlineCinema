package com.example.onlinecinema.data.remote

import com.example.onlinecinema.domain.movies.models.SearchMovie
import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("endReached") val endReached: Boolean?,
    @SerializedName("movies") val movies: List<SearchMovie>,
)