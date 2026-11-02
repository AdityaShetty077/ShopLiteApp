package com.aditya.shopliteapp.data.remote

import com.aditya.shopliteapp.data.remote.dto.toDomain
import com.aditya.shopliteapp.domain.model.Category
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.repository.ProductRepository
import com.aditya.shopliteapp.domain.result.AppError
import com.aditya.shopliteapp.domain.result.DataResult

// The real ProductRepository: API → DTO → domain → DataResult box
class RemoteProductRepository(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getProducts(): DataResult<List<Product>> {
        return try {
            val response = api.getProducts()                        // { products: [...] }
            DataResult.Success(response.products.map { it.toDomain() })
        } catch (e: Exception) {
            DataResult.Error(AppError.Unknown(e.message))           // Day 16: split into Network / Http
        }
    }

    override suspend fun getProduct(id: Int): DataResult<Product> {
        return try {
            DataResult.Success(api.getProduct(id).toDomain())
        } catch (e: Exception) {
            DataResult.Error(AppError.Unknown(e.message))
        }
    }

    override suspend fun getCategories(): DataResult<List<Category>> {
        return try {
            DataResult.Success(api.getCategories().map { it.toDomain() })
        } catch (e: Exception) {
            DataResult.Error(AppError.Unknown(e.message))
        }
    }
}