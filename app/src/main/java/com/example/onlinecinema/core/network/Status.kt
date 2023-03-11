package com.example.onlinecinema.core.network

enum class Status(val value: String) {

    // Success response
    OK("ok"),

    // Base errors
    UNAUTHORIZED("unauthorized"),
    FORBIDDEN("forbidden"),
    BAD_REQUEST("bad-request"),
    INTERNAL_ERROR("internal-error"),
    NOT_FOUND("not-found"),
    UNKNOWN("unknown"),
}