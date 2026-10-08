package com.aditya.shopliteapp.data.dummy

import com.aditya.shopliteapp.data.fake.Category
import com.aditya.shopliteapp.domain.model.Product

object FakeProductSource {

    val categories : List<Category> = listOf(
        Category(
            slug = "smartphones",
            name = "Smartphones"
        ),
        Category(
            slug = "laptops",
            name = "Laptops"
        ),
        Category(
            slug = "fragrances",
            name = "Fragrances"
        )
    )

    val products : List<Product> = listOf(
        Product(
            id = 121,
            title = "iPhone 5s",
            description = "Classic smartphone",
            category = "smartphones",
            price = 199.99,
            discountPercentage = 12.91,
            rating = 2.83,
            stock = 25,
            brand = "Apple",
            thumbnail = "https://cdn.dummyjson.com/product-images/smartphones/iphone-5s/thumbnail.webp",
            images = emptyList()
        ),

        Product(
            id = 122,
            title = "iPhone 6",
            description = "Stylish smartphone",
            category = "smartphones",
            price = 299.99,
            discountPercentage = 6.69,
            rating = 3.41,
            stock = 60,
            brand = "Apple",
            thumbnail = "https://cdn.dummyjson.com/product-images/smartphones/iphone-6/thumbnail.webp",
            images = emptyList()
        ),

        Product(
            id = 1,
            title = "Laptop 1",
            description = "Laptop product",
            category = "laptops",
            price = 999.99,
            discountPercentage = 10.0,
            rating = 4.5,
            stock = 20,
            brand = "Brand A",
            thumbnail = "PUT_LAPTOP_THUMBNAIL_URL_HERE",
            images = emptyList()
        ),

        Product(
            id = 2,
            title = "Laptop 2",
            description = "Laptop product",
            category = "laptops",
            price = 699.99,
            discountPercentage = 5.0,
            rating = 4.0,
            stock = 15,
            brand = null,
            thumbnail = "PUT_LAPTOP_THUMBNAIL_URL_HERE",
            images = emptyList()
        ),

        Product(
            id = 3,
            title = "Fragrance 1",
            description = "Fragrance product",
            category = "fragrances",
            price = 89.99,
            discountPercentage = 8.0,
            rating = 4.7,
            stock = 30,
            brand = "Brand B",
            thumbnail = "PUT_FRAGRANCE_THUMBNAIL_URL_HERE",
            images = emptyList()
        ),

        Product(
            id = 4,
            title = "Fragrance 2",
            description = "Fragrance product",
            category = "fragrances",
            price = 59.99,
            discountPercentage = 12.0,
            rating = 3.9,
            stock = 25,
            brand = "Brand C",
            thumbnail = "PUT_FRAGRANCE_THUMBNAIL_URL_HERE",
            images = emptyList()
        )
    )
}