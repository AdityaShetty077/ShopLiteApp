package com.aditya.shopliteapp.ui.home

import com.aditya.shopliteapp.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String? = null
)
