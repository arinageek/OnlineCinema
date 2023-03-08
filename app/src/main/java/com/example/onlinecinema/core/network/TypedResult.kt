package com.example.onlinecinema.core.network

sealed class TypedResult<T> {

    data class Success<T>(@Transient val result: T) : TypedResult<T>()

    data class Error<T>(@Transient val error: ApiError) : TypedResult<T>()
}