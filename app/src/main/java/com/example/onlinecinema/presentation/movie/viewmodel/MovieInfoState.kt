package com.example.onlinecinema.presentation.movie.viewmodel

data class MovieInfoState(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val year: Int = 0,
    val image: String = "",
    val views: String = "",
    val rating: Float = 0.0f,
    val genres: List<String> = emptyList(),
    val countries: List<String> = emptyList(),
    val duration: Long = 0L, // in seconds
    val isFavorite: Boolean = false,
)