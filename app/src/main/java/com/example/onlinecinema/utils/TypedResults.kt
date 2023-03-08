package com.example.onlinecinema.utils

import com.example.onlinecinema.core.network.ApiError
import com.example.onlinecinema.core.network.TypedResult
import java.util.concurrent.CancellationException

inline fun <S, R> S.runOperationCatching(block: S.() -> TypedResult<R>): TypedResult<R> {
    return try {
        block()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        TypedResult.Error(ApiError.UnknownError(e.message.toString()))
    }
}