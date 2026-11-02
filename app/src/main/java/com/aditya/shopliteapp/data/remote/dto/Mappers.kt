package com.aditya.shopliteapp.data.remote.dto

import com.aditya.shopliteapp.domain.model.Category
import com.aditya.shopliteapp.domain.model.Product

// server shape → app shape
fun ProductDto.toDomain(): Product = Product(
    id = id,
    title = title,
    description = description,
    category = category,
    price = price,
    discountPercentage = discountPercentage,
    rating = rating,
    stock = stock,
    brand = brand,
    thumbnail = thumbnail,
    images = images
)

fun CategoryDto.toDomain(): Category{
    return Category(
        slug = slug,
        name = name)
}