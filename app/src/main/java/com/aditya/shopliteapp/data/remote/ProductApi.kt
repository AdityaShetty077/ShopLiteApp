package com.aditya.shopliteapp.data.remote

import com.aditya.shopliteapp.data.remote.dto.CategoryDto
import com.aditya.shopliteapp.data.remote.dto.ProductDto
import com.aditya.shopliteapp.data.remote.dto.ProductsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Base URL (https://dummyjson.com/) is set once in AppContainer.
interface ProductApi {

    // GET products?limit=0  → all products (default is only 30)
    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int = 0): ProductsResponseDto

    // GET products/121
    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDto

    // GET products/categories  → a plain list, no wrapper
    @GET("products/categories")
    suspend fun getCategories(): List<CategoryDto>
}
