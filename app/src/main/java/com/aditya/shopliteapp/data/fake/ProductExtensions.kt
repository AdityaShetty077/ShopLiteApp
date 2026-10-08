package com.aditya.shopliteapp.data.fake

fun Product.discountedPrice(): Double {
    return price * (1 - discountPercentage / 100)
}
fun Double.toPrice(): String {
    return "$%.2f".format(this)
}

fun Product.brandOrDefault(): String {
    return brand ?: "Unknown brand"
}