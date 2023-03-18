package com.example.onlinecinema.domain.movies.models

import com.google.gson.annotations.SerializedName

data class SearchMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String,
)