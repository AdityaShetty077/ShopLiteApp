package com.aditya.shopliteapp.domain.result


fun AppError.toMessage(): String {
    return when (this) {
        AppError.Network -> "No internet connection. Check your connection and try again."

        AppError.Unauthorized -> "Your session has expired. Please log in again."

        is AppError.Http -> when (code) {
            404 -> "We couldn't find that."
            in 500..599 -> "Our server is having trouble. Please try again later."
            else -> "Something went wrong (error $code)."
        }

        // The raw message is for developers (Logcat), not for users
        is AppError.Unknown -> "Something went wrong. Please try again."
    }
}