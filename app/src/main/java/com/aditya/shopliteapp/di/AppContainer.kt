package com.aditya.shopliteapp.di

import com.aditya.shopliteapp.data.fake.FakeCartRepository
import com.aditya.shopliteapp.data.remote.ProductApi
import com.aditya.shopliteapp.data.remote.RemoteProductRepository
import com.aditya.shopliteapp.domain.repository.CartRepository
import com.aditya.shopliteapp.domain.repository.ProductRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object AppContainer {

    // 1. JSON parser: skip keys our DTOs don't declare (tags, sku, reviews…)
    private val json = Json { ignoreUnknownKeys = true }

    // 2. Retrofit: built once, shared by everyone
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")      // must end with "/"
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    // 3. The real API: Retrofit generates the class behind the interface
    val productApi: ProductApi = retrofit.create(ProductApi::class.java)
    val productRepository: ProductRepository = RemoteProductRepository(productApi)

    val cartRepository: CartRepository = FakeCartRepository()
}