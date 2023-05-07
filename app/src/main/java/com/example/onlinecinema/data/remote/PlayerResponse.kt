package com.example.onlinecinema.data.remote

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("link") val link: String,
    @SerializedName("startFrom") val startFrom: Long?,
)
