package com.example.onlinecinema.core.network

import com.google.gson.annotations.SerializedName

sealed class ApiError : WithMessage {

    data class UnknownError(
        @SerializedName("message") override val message: String,
    ) : ApiError()
}

interface WithMessage {
    val message: String
}
