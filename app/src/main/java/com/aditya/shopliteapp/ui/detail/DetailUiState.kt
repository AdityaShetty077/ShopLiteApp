package com.aditya.shopliteapp.ui.detail

import com.aditya.shopliteapp.domain.model.Product

data class DetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val errorMessage: String? = null,

)