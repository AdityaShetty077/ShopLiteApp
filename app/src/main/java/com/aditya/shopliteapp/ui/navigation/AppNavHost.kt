package com.aditya.shopliteapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aditya.shopliteapp.ui.cart.CartScreen
import com.aditya.shopliteapp.ui.detail.ProductDetailScreen
import com.aditya.shopliteapp.ui.home.HomeScreen
import com.aditya.shopliteapp.ui.profile.ProfileScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    onShowMessage: (String) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute,          // first screen when the app opens
        modifier = modifier
    ) {
        // "when the route is HomeRoute, draw HomeScreen"
        composable<HomeRoute> {
            HomeScreen(
                onProductClick = { id -> navController.navigate(ProductDetailRoute(productId = id)) }
            )
        }
        // "when the route is ProductDetailRoute, draw the detail screen"
        composable<ProductDetailRoute> {
            ProductDetailScreen(
                onBack = { navController.popBackStack() },
                onAddedToCart = { onShowMessage("Added to cart") }
            )
        }
        composable<CartRoute>
        {
            CartScreen()
        }
        composable<ProfileRoute>
        {
            ProfileScreen()
        }
    }
}