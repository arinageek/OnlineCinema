package com.example.onlinecinema.core.network

import java.lang.reflect.Type

object DefaultErrorMapping {
    val statusToErrorType = mapOf<String, Type>(
        Status.UNKNOWN.value to ApiError.UnknownError::class.java,
        Status.NOT_FOUND.value to ApiError.NotFound::class.java,
        Status.INTERNAL_ERROR.value to ApiError.InternalError::class.java,
        Status.BAD_REQUEST.value to ApiError.BadRequest::class.java,
        Status.FORBIDDEN.value to ApiError.Forbidden::class.java,
        Status.UNAUTHORIZED.value to ApiError.Unauthorized::class.java,
    )
}