package com.aditya.shopliteapp.data.remote.dto

import kotlinx.serialization.Serializable

// /products/categories returns [ { "slug": .., "name": .., "url": .. }, ... ]
@Serializable
data class CategoryDto(
    val slug: String,
    val name: String                    // "url" is skipped — the app doesn't need it
)