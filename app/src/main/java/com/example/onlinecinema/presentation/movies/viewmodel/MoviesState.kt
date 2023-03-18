package com.example.onlinecinema.presentation.movies.viewmodel

import com.example.onlinecinema.domain.movies.models.SearchMovie

data class MoviesState(
    val query: String = "",
    val movies: List<SearchMovie> = emptyList(),
    val page: Int = 1,
    val endReached: Boolean = false,
)