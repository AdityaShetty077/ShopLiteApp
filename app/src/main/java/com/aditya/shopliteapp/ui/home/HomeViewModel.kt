package com.aditya.shopliteapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopliteapp.data.fake.FakeProductRepository
import com.aditya.shopliteapp.domain.model.SortOrder
import com.aditya.shopliteapp.domain.repository.ProductRepository
import com.aditya.shopliteapp.domain.result.DataResult
import com.aditya.shopliteapp.domain.result.toMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository = FakeProductRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val categoriesResult = repository.getCategories()
            val categories = if (categoriesResult is DataResult.Success) categoriesResult.data else emptyList()
            when (val result = repository.getProducts()) {
                is DataResult.Success -> _uiState.update { it.copy(isLoading = false,products = result.data, categories = categories) }
                is DataResult.Error -> _uiState.update { it.copy(isLoading = false,errorMessage = result.error.toMessage()) }
            }

        }
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onCategorySelected(slug: String?) {
        _uiState.update { it.copy(selectedCategory = slug) }
    }

    fun onSortSelected(order: SortOrder) {
        _uiState.update { it.copy(sortOrder = order) }
    }

}