package com.aditya.shopliteapp.data.fake

enum class SortOrder(
    val label : String
) {
    PRICE_LOW_TO_HIGH("Price: Low to High"),
    PRICE_HIGH_TO_LOW("Price: High to Low"),
    RATING("Rating")
}