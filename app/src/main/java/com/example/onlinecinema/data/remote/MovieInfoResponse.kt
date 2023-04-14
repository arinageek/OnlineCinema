package com.example.onlinecinema.data.remote

import com.google.gson.annotations.SerializedName

data class MovieInfoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("year") val year: Int,
    @SerializedName("image") val image: String,
    @SerializedName("views") val views: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("genres") val genres: List<String>,
    @SerializedName("countries") val countries: List<String>,
    @SerializedName("duration") val duration: Long, // in seconds
    @SerializedName("isFavorite") val isFavorite: Boolean,
)
