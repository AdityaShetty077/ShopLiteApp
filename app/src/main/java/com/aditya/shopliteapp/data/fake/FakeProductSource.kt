package com.aditya.shopliteapp.data.fake

import com.aditya.shopliteapp.domain.model.Category
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
            id = 78,
            title = "Apple MacBook Pro 14 Inch Space Grey",
            description = "Powerful and sleek laptop with Apple's M1 Pro chip and a Retina display",
            category = "laptops",
            price = 1999.99,
            discountPercentage = 4.69,
            rating = 3.65,
            stock = 24,
            brand = "Apple",
            thumbnail = "https://cdn.dummyjson.com/product-images/laptops/apple-macbook-pro-14-inch-space-grey/thumbnail.webp",
            images = emptyList()
        ),

        Product(
            id = 79,
            title = "Asus Zenbook Pro Dual Screen Laptop",
            description = "High-performance laptop with dual screens for creative professionals",
            category = "laptops",
            price = 1799.99,
            discountPercentage = 11.14,
            rating = 3.95,
            stock = 45,
            brand = null,
            thumbnail = "https://cdn.dummyjson.com/product-images/laptops/asus-zenbook-pro-dual-screen-laptop/thumbnail.webp",
            images = emptyList()
        ),

        Product(
            id = 6,
            title = "Calvin Klein CK One",
            description = "Classic unisex fragrance with a fresh and clean scent",
            category = "fragrances",
            price = 49.99,
            discountPercentage = 1.89,
            rating = 4.37,
            stock = 29,
            brand = "Calvin Klein",
            thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/calvin-klein-ck-one/thumbnail.webp",
            images = emptyList()
        ),

        Product(
            id = 7,
            title = "Chanel Coco Noir Eau De",
            description = "Elegant fragrance with notes of grapefruit, rose and sandalwood",
            category = "fragrances",
            price = 129.99,
            discountPercentage = 16.51,
            rating = 4.26,
            stock = 58,
            brand = "Chanel",
            thumbnail = "https://cdn.dummyjson.com/product-images/fragrances/chanel-coco-noir-eau-de/thumbnail.webp",
            images = emptyList()
        )
    )
}