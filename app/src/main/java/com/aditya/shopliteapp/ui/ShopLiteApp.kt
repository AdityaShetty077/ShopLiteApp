package com.aditya.shopliteapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aditya.shopliteapp.ui.cart.CartViewModel
import com.aditya.shopliteapp.ui.navigation.AppNavHost
import com.aditya.shopliteapp.ui.navigation.CartRoute
import com.aditya.shopliteapp.ui.navigation.HomeRoute
import com.aditya.shopliteapp.ui.navigation.ProfileRoute
import kotlinx.coroutines.launch

@Composable
fun ShopLiteApp() {
    // created HERE so the tabs and the NavHost share the same one
    val navController = rememberNavController()
    // which screen is on top right now? (state → tabs redraw when it changes)
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val cartViewModel: CartViewModel = viewModel()
    val cart by cartViewModel.cart.collectAsStateWithLifecycle()
    val cartCount = cart.sumOf { it.quantity }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination?.hasRoute(HomeRoute::class) == true,
                    onClick = { navController.navigateToTab(HomeRoute) },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = currentDestination?.hasRoute(CartRoute::class) == true,
                    onClick = { navController.navigateToTab(CartRoute) },
                    icon = {
                        BadgedBox(badge = { if (cartCount > 0) Badge { Text("$cartCount") } }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = null)
                        }
                    },
                    label = { Text("Cart") }
                )
                NavigationBarItem(
                    selected = currentDestination?.hasRoute(ProfileRoute::class) == true,
                    onClick = { navController.navigateToTab(ProfileRoute) },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            onShowMessage = { message -> scope.launch { snackbarHostState.showSnackbar(message) } },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

// switch tabs without piling up screens on the back stack
private fun NavController.navigateToTab(route: Any) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }  // clear back to Home, remember tab state
        launchSingleTop = true                                         // no duplicate if already on this tab
        restoreState = true                                            // come back to where you were on that tab
    }
}