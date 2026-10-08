package com.aditya.shopliteapp.domain.result

sealed interface DataResult <out T> {

    data class Success<T>(
        val data: T
    ) : DataResult<T>

    data class Error(
        val error: AppError
    ) : DataResult<Nothing>
}