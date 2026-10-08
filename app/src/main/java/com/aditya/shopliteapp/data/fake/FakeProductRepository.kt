package com.aditya.shopliteapp.data.fake

import com.aditya.shopliteapp.domain.model.Category
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.repository.ProductRepository
import com.aditya.shopliteapp.domain.result.AppError
import com.aditya.shopliteapp.domain.result.DataResult
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class FakeProductRepository : ProductRepository {
    override suspend fun getProducts(): DataResult<List<Product>> {
        delay(1000.milliseconds)
        return DataResult.Success(FakeProductSource.products)
    }

    override suspend fun getProduct(id: Int): DataResult<Product> {
        delay(1000.milliseconds)

        val product = FakeProductSource.products.find { it.id == id }

        return if (product != null) {
            DataResult.Success(product)
        } else {
            DataResult.Error(AppError.Http(404))
        }
    }

    override suspend fun getCategories(): DataResult<List<Category>> {
        delay(1000.milliseconds)
        return DataResult.Success(FakeProductSource.categories)
    }
}