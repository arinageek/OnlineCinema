package com.example.onlinecinema.core.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

sealed class ApiError : Parcelable, WithMessage {

    @Parcelize
    data class Unauthorized(@SerializedName("message") override val message: String) : ApiError()

    @Parcelize
    data class InternalError(@SerializedName("message") override val message: String) : ApiError()

    @Parcelize
    data class NotFound(@SerializedName("message") override val message: String) : ApiError()

    @Parcelize
    data class UnknownError(@SerializedName("message") override val message: String) : ApiError()

    @Parcelize
    data class Forbidden(@SerializedName("message") override val message: String) : ApiError()

    @Parcelize
    data class BadRequest(@SerializedName("messages") override val message: String) : ApiError()

}

interface WithMessage {
    val message: String
}
