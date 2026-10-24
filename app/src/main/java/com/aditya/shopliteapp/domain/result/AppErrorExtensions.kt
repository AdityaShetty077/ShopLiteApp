package com.aditya.shopliteapp.domain.result

fun AppError.toMessage() : String{
    return when(this)
    {
        AppError.Network -> "Network not Available"
        AppError.Unauthorized -> "Not Authorized"
        is AppError.Http -> "$code : Server Error"
        is AppError.Unknown -> message?: "Something went wrong"
    }

}