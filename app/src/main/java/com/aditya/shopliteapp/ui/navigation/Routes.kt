package com.aditya.shopliteapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class ProductDetailRoute(val productId: Int)

@Serializable
data object CartRoute
@Serializable
data object ProfileRoute