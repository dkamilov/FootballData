package com.damir.android.myscore

import android.util.Log
import com.damir.android.myscore.utils.TAG
import retrofit2.HttpException

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    sealed class Error: Result<Nothing>() {
        data class HttpError(val exception: Exception) : Error()
        data class NetworkError(val exception: Exception): Error()
    }
    object Loading : Result<Nothing>()
}

suspend fun <T : Any> safeResult(result: suspend () -> T): Result<T> {
    return try {
        val res = result.invoke()
        Result.Success(res)
    } catch (e: HttpException) {
        Log.e(TAG, "safeResult: ${e.message}")
        Result.Error.HttpError(e)
    } catch (e: Exception) {
        Log.e(TAG, "safeResult: ${e.message}")
        Result.Error.NetworkError(e)
    }
}