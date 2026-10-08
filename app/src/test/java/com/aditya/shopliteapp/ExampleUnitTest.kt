package com.aditya.shopliteapp

import com.aditya.shopliteapp.data.dummy.FakeProductSource
import com.aditya.shopliteapp.domain.model.CartItem
import com.aditya.shopliteapp.domain.model.Product
import com.aditya.shopliteapp.domain.model.SortOrder
import com.aditya.shopliteapp.domain.model.brandOrDefault
import com.aditya.shopliteapp.domain.model.discountedPrice
import com.aditya.shopliteapp.domain.model.filterByCategory
import com.aditya.shopliteapp.domain.model.searchByTitle
import com.aditya.shopliteapp.domain.model.sortBy
import com.aditya.shopliteapp.domain.model.toPrice
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun day2_models() {
        val product = Product(
            id = 1,
            title = "iPhone",
            description = "Apple iPhone",
            category = "smartphones",
            price = 100.0,
            discountPercentage = 20.0,
            rating = 4.5,
            stock = 10,
            brand = null,
            thumbnail = "iphone.jpg",
            images = listOf("iphone1.jpg")
        )

        val cartItem = CartItem(
            product = product,
            quantity = 3
        )

        println(product.discountedPrice().toPrice()) // $80.00
        println(product.brandOrDefault())            // Unknown brand
        println(cartItem.totalPrice)                 // 240.0
        println(product == product.copy())           // true
        println(product === product.copy())          // false
    }

    @Test

    fun testProductExtensions() {

        println(
            FakeProductSource.products
                .filterByCategory("laptops")
        )

        println(
            FakeProductSource.products
                .searchByTitle("IPHONE")
        )

        println(
            FakeProductSource.products
                .sortBy(SortOrder.PRICE_LOW_TO_HIGH)
                .map { it.title }
        )
    }
}