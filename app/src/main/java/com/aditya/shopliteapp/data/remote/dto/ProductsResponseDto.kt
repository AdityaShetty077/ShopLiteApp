package com.aditya.shopliteapp.data.remote.dto

import kotlinx.serialization.Serializable

// /products?limit=0 returns { "products": [...], "total": .., "skip": .., "limit": .. }
@Serializable
data class ProductsResponseDto(
    val products: List<ProductDto>      // the only part we need; total/skip/limit are ignored
)