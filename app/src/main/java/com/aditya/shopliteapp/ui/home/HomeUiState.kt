package com.aditya.shopliteapp.ui.home

import com.aditya.shopliteapp.domain.model.Category
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.model.SortOrder
import com.aditya.shopliteapp.domain.model.filterByCategory
import com.aditya.shopliteapp.domain.model.searchByTitle
import com.aditya.shopliteapp.domain.model.sortBy

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val errorMessage: String? = null,
    val query: String = "",
    val selectedCategory: String? = null,
    val sortOrder: SortOrder = SortOrder.RATING
) {
    val visibleProducts: List<Product>
        get() {
            val inCategory =
                if (selectedCategory == null) products else products.filterByCategory(selectedCategory)
            return inCategory.searchByTitle(query).sortBy(sortOrder)
        }
}
