package com.aditya.shopliteapp.domain.model

data class CartItem (
    val product : Product,
    val quantity : Int
)
{
    val totalPrice : Double
        get() = product.discountedPrice() * quantity
}