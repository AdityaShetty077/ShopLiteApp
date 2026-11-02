package com.aditya.shopliteapp.data.remote

import com.aditya.shopliteapp.domain.result.AppError
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

// Turns whatever Retrofit threw into one of our AppErrors.
// Written once, used in every catch block.
fun Throwable.toAppError(): AppError {
    return when (this) {
        // Screen closed → coroutine cancelled. Not an error: pass it on.
        is CancellationException -> throw this

        // No answer at all: no internet, airplane mode, timeout
        is IOException -> AppError.Network

        // The server answered, but with an error code (401, 404, 500…)
        is HttpException -> if (code() == 401) AppError.Unauthorized else AppError.Http(code())

        // Anything else, e.g. JSON that doesn't match the DTO
        else -> AppError.Unknown(message)
    }
}


