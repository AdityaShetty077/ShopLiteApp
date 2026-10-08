package com.aditya.shopliteapp.domain.result

fun AppError.toMessage(): String=

        when(this) {
          AppError.Network -> "No internet connection"
          AppError.Unauthorized -> "Session expired. Please log in again"
          is AppError.Http -> "Server error (${this.code})"
          is AppError.Unknown -> message?:"Something went wrong"
      }
