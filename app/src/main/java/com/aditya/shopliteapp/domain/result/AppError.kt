package com.aditya.shopliteapp.domain.result

sealed class AppError {

    data object Network : AppError()
    data object Unauthorized : AppError()
    data class Http (val code : Int): AppError()
    data class Unknown(val message: String?) : AppError()

}