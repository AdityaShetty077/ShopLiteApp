package com.aditya.shopliteapp.domain.repository

import com.aditya.shopliteapp.domain.model.Category
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.result.DataResult

interface ProductRepository {

    suspend fun getProducts(): DataResult<List<Product>>

    suspend fun getProduct(id: Int): DataResult<Product>

    suspend fun getCategories(): DataResult<List<Category>>
}