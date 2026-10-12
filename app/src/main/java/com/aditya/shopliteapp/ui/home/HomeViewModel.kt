package com.aditya.shopliteapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopliteapp.data.fake.FakeProductRepository
import com.aditya.shopliteapp.domain.repository.ProductRepository
import com.aditya.shopliteapp.domain.result.DataResult
import com.aditya.shopliteapp.domain.result.toMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository = FakeProductRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            when (val result = repository.getProducts()) {
                is DataResult.Success -> _uiState.value = HomeUiState(products = result.data)
                is DataResult.Error -> _uiState.value = HomeUiState(errorMessage = result.error.toMessage())
            }
        }
    }
}