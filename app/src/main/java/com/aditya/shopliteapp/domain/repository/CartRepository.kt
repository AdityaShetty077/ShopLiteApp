package com.aditya.shopliteapp.domain.repository

import com.aditya.shopliteapp.domain.model.CartItem
import com.aditya.shopliteapp.domain.model.Product
import kotlinx.coroutines.flow.StateFlow

interface CartRepository {
    val cart: StateFlow<List<CartItem>>
    suspend fun add(product: Product)
    suspend fun remove(productId: Int)
    suspend fun changeQuantity(productId: Int, quantity: Int)
}