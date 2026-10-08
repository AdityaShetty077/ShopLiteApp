package com.aditya.shopliteapp.domain.model

fun Product.discountedPrice(): Double {
    return price * (1 - discountPercentage / 100)
}
fun Double.toPrice(): String {
    return "$%.2f".format(this)
}

fun Product.brandOrDefault(): String {
    return brand ?: "Unknown brand"
}

fun List<Product>.filterByCategory(category: String): List<Product> {
    return filter {
        it.category.equals(category, ignoreCase = true)
    }
}

fun List<Product>.searchByTitle(query: String): List<Product> {
    return filter {
        it.title.contains(query, ignoreCase = true)
    }
}

fun List<Product>.sortBy(order : SortOrder) : List<Product>
{
    return when(order)
    {
        SortOrder.PRICE_LOW_TO_HIGH -> sortedBy { it.price }
        SortOrder.PRICE_HIGH_TO_LOW -> sortedByDescending { it.price }
        SortOrder.RATING -> sortedByDescending { it.rating }
    }
}