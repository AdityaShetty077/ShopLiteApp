package com.aditya.shopliteapp.di

import com.aditya.shopliteapp.data.fake.FakeCartRepository
import com.aditya.shopliteapp.domain.repository.CartRepository

object AppContainer {
    val cartRepository: CartRepository = FakeCartRepository()
}
