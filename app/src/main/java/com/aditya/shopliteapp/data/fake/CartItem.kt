package com.aditya.shopliteapp.data.fake

data class CartItem (
    val product : Product,
    val quantity : Int
)
{
    val totalPrice : Double
        get() = product.discountedPrice() * quantity
}