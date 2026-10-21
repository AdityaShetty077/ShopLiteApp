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

    val cart: StateFlow<List<CartItem>> = cartRepository.cart

    fun increase(item: CartItem) {
        viewModelScope.launch { cartRepository.changeQuantity(item.product.id, item.quantity + 1) }
    }

    fun decrease(item: CartItem) {
        viewModelScope.launch { cartRepository.changeQuantity(item.product.id, item.quantity - 1) }
    }

    fun remove(item: CartItem) {
        viewModelScope.launch { cartRepository.remove(item.product.id) }
    }
}
