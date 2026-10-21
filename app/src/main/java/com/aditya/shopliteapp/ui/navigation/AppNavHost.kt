package com.aditya.shopliteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aditya.shopliteapp.ui.cart.CartScreen
import com.aditya.shopliteapp.ui.detail.ProductDetailScreen
import com.aditya.shopliteapp.ui.home.HomeScreen
import com.aditya.shopliteapp.ui.profile.ProfileScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onProductClick = { id -> navController.navigate(ProductDetailRoute(productId = id)) }
            )
        }
        composable<ProductDetailRoute> {
            ProductDetailScreen(
                onBack = { navController.popBackStack() },
                onAddedToCart = { onShowMessage("Added to cart") }
            )
        }
        composable<CartRoute> {
            CartScreen()
        }
        composable<ProfileRoute> {
            ProfileScreen()
        }
    }
}
