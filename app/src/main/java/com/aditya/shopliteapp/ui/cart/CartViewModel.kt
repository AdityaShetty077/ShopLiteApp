package com.aditya.shopliteapp.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.shopliteapp.di.AppContainer
import com.aditya.shopliteapp.domain.model.CartItem
import com.aditya.shopliteapp.domain.repository.CartRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository = AppContainer.cartRepository
) : ViewModel() {

    // no _uiState needed: the repository already exposes the cart as a StateFlow
    val cart: StateFlow<List<CartItem>> = cartRepository.cart

    // + button
    fun increase(item: CartItem) {
        viewModelScope.launch { cartRepository.changeQuantity(item.product.id, item.quantity + 1) }
    }

    // − button (quantity 1 → 0 → your Day 6 code removes the row)
    fun decrease(item: CartItem) {
        viewModelScope.launch { cartRepository.changeQuantity(item.product.id, item.quantity - 1) }
    }

    // remove button
    fun remove(item: CartItem) {
        viewModelScope.launch { cartRepository.remove(item.product.id) }
    }
}