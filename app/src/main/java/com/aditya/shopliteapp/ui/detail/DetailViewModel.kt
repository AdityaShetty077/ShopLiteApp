package com.aditya.shopliteapp.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.aditya.shopliteapp.data.fake.FakeProductRepository
import com.aditya.shopliteapp.domain.repository.ProductRepository
import com.aditya.shopliteapp.domain.result.DataResult
import com.aditya.shopliteapp.domain.result.toMessage
import com.aditya.shopliteapp.ui.navigation.ProductDetailRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel @JvmOverloads constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ProductRepository = FakeProductRepository()
) : ViewModel(){
    private val productId = savedStateHandle.toRoute<ProductDetailRoute>().productId
    private val _uiState = MutableStateFlow(DetailUiState(isLoading = true))
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        loadProduct()
    }

    fun loadProduct()
    {
        viewModelScope.launch {
            _uiState.value = DetailUiState(isLoading = true)
            when (val result = repository.getProduct(productId)) {
                is DataResult.Success -> _uiState.value = DetailUiState(product = result.data)
                is DataResult.Error -> _uiState.value = DetailUiState(errorMessage = result.error.toMessage())
            }
        }
    }
}