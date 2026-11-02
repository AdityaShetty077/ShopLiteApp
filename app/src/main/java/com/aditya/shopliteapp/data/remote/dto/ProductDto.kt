package com.aditya.shopliteapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String? = null,                 // some products have NO "brand" key at all
    val thumbnail: String,
    val images: List<String> = emptyList()     // safe default if missing
)