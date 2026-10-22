package com.aditya.shopliteapp.domain.model


fun Product.discountedPrice(): Double {
    return (1 - discountPercentage / 100) * price
}

fun Double.toPrice(): String {
    return "$%.2f".format(this)
}

fun Product.brandOrDefault(): String {
    return brand ?: "Unknown brand"
}

fun List<Product>.filterByCategory(category: String): List<Product> {
    return filter { it.category.equals(category, ignoreCase = true) }
}

fun List<Product>.searchByTitle(query: String): List<Product> {
    return filter { it.title.contains(query, ignoreCase = true) }
}