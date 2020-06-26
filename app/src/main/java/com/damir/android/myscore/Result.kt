package com.damir.android.myscore

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    sealed class Error: Result<Nothing>() {
        data class HttpError(val exception: Exception) : Error()
        data class NetworkError(val exception: Exception): Error()
    }
    object Loading : Result<Nothing>()
}